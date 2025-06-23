package com.tool.workflow.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tool.workflow.security.jwt.AuthEntryPointJwt;
import com.tool.workflow.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

//		http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//				.ignoringRequestMatchers("/api/auth/public/**"));
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/api/admin/**").hasRole("PRODUCTOWNER")
				.requestMatchers("/api/scrum-master/**").hasRole("SCRUMMASTER").requestMatchers("/api/sdet-engineer")
				.hasRole("SDETENGINEER").requestMatchers("/api/csrf-token").permitAll()
				.requestMatchers("/api/auth/public/**").permitAll().requestMatchers("/api/auth/public/**").permitAll()
				.anyRequest().authenticated());
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.formLogin(withDefaults());
		http.csrf(csrf -> csrf.disable());
		// http.httpBasic(withDefaults());
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
