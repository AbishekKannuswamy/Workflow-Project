package com.tool.workflow.service;

import java.util.List;
import java.util.Optional;

import com.tool.workflow.dto.UserDTO;
import com.tool.workflow.model.Role;
import com.tool.workflow.model.User;

public interface UserService {
	void updateUserRole(Long userId, String roleName);

	List<User> getAllUsers();

	UserDTO getUserById(Long id);

	User findByUsername(String username);

	void updateAccountLockStatus(Long userId, boolean lock);

	List<Role> getAllRoles();

	void updateAccountExpiryStatus(Long userId, boolean expire);

	void updateAccountEnabledStatus(Long userId, boolean enabled);

	void updateCredentialsExpiryStatus(Long userId, boolean expire);

	void updatePassword(Long userId, String password);

	void generatePasswordResetToken(String email);

	void resetPassword(String token, String newPassword);

	Optional<User> findByEmail(String email);

	User registerUser(User user);
}
