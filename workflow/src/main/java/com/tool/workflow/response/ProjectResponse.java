package com.tool.workflow.response;

import lombok.Data;

@Data
public class ProjectResponse {
	private String projectName;

	private String projectDescription;

	private String projectStatus;

	public ProjectResponse(String projectName, String projectDescription, String projectStatus) {
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectStatus = projectStatus;
	}

	public ProjectResponse() {

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

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
}
