package com.tool.workflow.request;

import java.util.Map;

import lombok.Data;

@Data
public class ScrumTeamRequest {

	private String projectName;
	private String scrumTeamName;
	private Map<String, String> emailAndRole;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getScrumTeamName() {
		return scrumTeamName;
	}

	public void setScrumTeamName(String scrumTeamName) {
		this.scrumTeamName = scrumTeamName;
	}

	public Map<String, String> getEmailAndRole() {
		return emailAndRole;
	}

	public void setEmailAndRole(Map<String, String> emailAndRole) {
		this.emailAndRole = emailAndRole;
	}

}
