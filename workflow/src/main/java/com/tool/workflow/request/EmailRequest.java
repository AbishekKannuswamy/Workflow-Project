package com.tool.workflow.request;

import java.util.List;

import lombok.Data;

@Data
public class EmailRequest {

	private String projectName;

	private List<String> emails;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

}
