package com.tool.workflow.request;

import java.sql.Date;

import lombok.Data;

@Data
public class SprintRequest {

	private String sprintGoal;
	private Date startDate;
	private Date endDate;
	private String epicTitle;

	public String getSprintGoal() {
		return sprintGoal;
	}

	public void setSprintGoal(String sprintGoal) {
		this.sprintGoal = sprintGoal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEpicTitle() {
		return epicTitle;
	}

	public void setEpicTitle(String epicTitle) {
		this.epicTitle = epicTitle;
	}

}
