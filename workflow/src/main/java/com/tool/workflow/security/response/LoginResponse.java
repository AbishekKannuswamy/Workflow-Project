package com.tool.workflow.security.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
	private String jwtToken;
	private String username;
	private List<String> roles;

	public LoginResponse(String username, List<String> roles, String jwtToken) {
		this.username = username;
		this.roles = roles;
		this.jwtToken = jwtToken;
	}

	public LoginResponse() {

	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}