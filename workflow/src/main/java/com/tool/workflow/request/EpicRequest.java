package com.tool.workflow.request;

import lombok.Data;

@Data
public class EpicRequest {

	private String projectName;

	private String epicTitle;

	private String epicDescription;

	private int noOfSprints;

	public EpicRequest() {

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

	public int getNoOfSprints() {
		return noOfSprints;
	}

	public void setNoOfSprints(int noOfSprints) {
		this.noOfSprints = noOfSprints;
	}
}
