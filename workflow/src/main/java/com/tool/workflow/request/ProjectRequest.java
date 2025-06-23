package com.tool.workflow.request;

import lombok.Data;

@Data
public class ProjectRequest {

	private String projectName;

	private String projectDescription;

	public ProjectRequest(String projectName, String projectDescription) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;

	}

	public ProjectRequest() {

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

}
