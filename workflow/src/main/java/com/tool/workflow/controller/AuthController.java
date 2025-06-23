package com.tool.workflow.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.model.AppRole;
import com.tool.workflow.model.Project;
import com.tool.workflow.model.ProjectMembers;
import com.tool.workflow.model.Role;
import com.tool.workflow.model.ScrumTeams;
import com.tool.workflow.model.User;
import com.tool.workflow.repository.ProjectMembersRepository;
import com.tool.workflow.repository.ProjectRepository;
import com.tool.workflow.repository.RoleRepository;
import com.tool.workflow.repository.ScrumTeamsRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.security.jwt.JwtUtils;
import com.tool.workflow.security.request.LoginRequest;
import com.tool.workflow.security.request.SignupRequest;
import com.tool.workflow.security.response.LoginResponse;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.security.response.UserInfoResponse;
import com.tool.workflow.security.services.UserDetailsImpl;
import com.tool.workflow.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ProjectMembersRepository projectMembersRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ScrumTeamsRepository scrumTeamsRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@PostMapping("/public/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		} catch (AuthenticationException exception) {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "Bad credentials");
			map.put("status", false);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}

//	      Set the authentication
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

		// Collect roles from the UserDetails
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// Prepare the response body, now including the JWT token directly in the body
		LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

		// Return the response entity with the JWT token included in the response body
		return ResponseEntity.ok(response);
	}

	@PostMapping("/public/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		Set<String> strRoles = signUpRequest.getRole();
		Role role;
		String roleStr = strRoles.iterator().next();

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {

			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));

		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		if (strRoles == null || strRoles.isEmpty()) {
			role = roleRepository.findByRoleName(AppRole.ROLE_PRODUCTOWNER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		} else {

			if (roleStr.equals("productOwner")) {
				role = roleRepository.findByRoleName(AppRole.ROLE_PRODUCTOWNER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			}

			else if (roleStr.equals("scrumMaster")) {
				role = roleRepository.findByRoleName(AppRole.ROLE_SCRUMMASTER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			}

			else {
				role = roleRepository.findByRoleName(AppRole.ROLE_SDETENGINEER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			}

			user.setAccountNonLocked(true);
			user.setAccountNonExpired(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			user.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
			user.setAccountExpiryDate(LocalDate.now().plusYears(1));
			user.setTwoFactorEnabled(false);
			user.setSignUpMethod("email");
		}

		if (projectMembersRepository.existsByEmail(user.getEmail())) {
			ProjectMembers projectMember = projectMembersRepository.findByEmail(user.getEmail()).orElseThrow();
			ScrumTeams scrumTeam = projectMember.getScrumTeam();
			Project project = scrumTeam.getProject();
			if (projectMember.getRole().equals("scrumMaster")) {

				if (project.getScrumMasterCode().equals(signUpRequest.getOtp())) {
					user.setRole(role);
				} else {
					return ResponseEntity.badRequest().body(new MessageResponse("Error: OTP is invalid"));
				}

			} else if (projectMember.getRole().equals("sdetEngineer")) {
				if (project.getsDETEngineerCode().equals(signUpRequest.getOtp())) {
					user.setRole(role);
				} else {
					return ResponseEntity.badRequest().body(new MessageResponse("Error: OTP is invalid"));
				}
			}

		}
		user.setRole(role);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	@GetMapping("/user")
	public ResponseEntity<?> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		UserInfoResponse response = new UserInfoResponse(user.getUserId(), user.getUserName(), user.getEmail(),
				user.isAccountNonLocked(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isEnabled(),
				user.getCredentialsExpiryDate(), user.getAccountExpiryDate(), user.isTwoFactorEnabled(), roles);

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/username")
	public String currentUserName(@AuthenticationPrincipal UserDetails userDetails) {
		return (userDetails != null) ? userDetails.getUsername() : "";
	}

	@PostMapping("/public/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestParam String email) {
		try {
			userService.generatePasswordResetToken(email);
			return ResponseEntity.ok(new MessageResponse("Password reset email sent!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new MessageResponse("Error sending password reset email"));
		}
	}

	@PostMapping("/public/reset-password")
	public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {

		try {
			userService.resetPassword(token, newPassword);
			return ResponseEntity.ok(new MessageResponse("Password reset successful"));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(e.getMessage()));
		}
	}
}
