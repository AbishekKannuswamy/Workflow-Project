package com.tool.workflow.response;

public class EpicResponse {

	private String projectName;

	private String epicTitle;

	private String epicDescription;

	private String epicStatus;

	private int noOfSprints;

	public EpicResponse() {

	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEpicTitle() {
		return epicTitle;
	}

	public void setEpicTitle(String epicTitle) {
		this.epicTitle = epicTitle;
	}

	public String getEpicDescription() {
		return epicDescription;
	}

	public void setEpicDescription(String epicDescription) {
		this.epicDescription = epicDescription;
	}

	public String getEpicStatus() {
		return epicStatus;
	}

	public void setEpicStatus(String epicStatus) {
		this.epicStatus = epicStatus;
	}

	public int getNoOfSprints() {
		return noOfSprints;
	}

	public void setNoOfSprints(int noOfSprints) {
		this.noOfSprints = noOfSprints;
	}
}
