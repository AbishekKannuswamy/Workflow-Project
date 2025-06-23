package com.tool.workflow.request;

import java.sql.Date;

import lombok.Data;

@Data
public class TaskRequest {

	private String taskTitle;
	private String taskDescription;
	private Date dueDate;
	private String priority;
	private String userStoryTitle;

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getUserStoryTitle() {
		return userStoryTitle;
	}

	public void setUserStoryTitle(String userStoryTitle) {
		this.userStoryTitle = userStoryTitle;
	}

}
