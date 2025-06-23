package com.tool.workflow.request;

import lombok.Data;

@Data
public class UserStoryRequest {

	private String userStoryTitle;

	private String userStoryDescription;

	private String userStoryAcceptanceCriteria;

	private String userStoryPriority;

	private String epicTitle;

	public String getUserStoryTitle() {
		return userStoryTitle;
	}

	public void setUserStoryTitle(String userStoryTitle) {
		this.userStoryTitle = userStoryTitle;
	}

	public String getUserStoryDescription() {
		return userStoryDescription;
	}

	public void setUserStoryDescription(String userStoryDescription) {
		this.userStoryDescription = userStoryDescription;
	}

	public String getUserStoryAcceptanceCriteria() {
		return userStoryAcceptanceCriteria;
	}

	public void setUserStoryAcceptanceCriteria(String userStoryAcceptanceCriteria) {
		this.userStoryAcceptanceCriteria = userStoryAcceptanceCriteria;
	}

	public String getUserStoryPriority() {
		return userStoryPriority;
	}

	public void setUserStoryPriority(String userStoryPriority) {
		this.userStoryPriority = userStoryPriority;
	}

	public String getEpicTitle() {
		return epicTitle;
	}

	public void setEpicTitle(String epicTitle) {
		this.epicTitle = epicTitle;
	}

}
