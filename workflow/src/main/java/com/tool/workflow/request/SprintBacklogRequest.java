package com.tool.workflow.request;

import java.util.List;

import lombok.Data;

@Data
public class SprintBacklogRequest {

	private List<String> userStoryTitles;

	public List<String> getUserStoryTitles() {
		return userStoryTitles;
	}

	public void setUserStoryTitles(List<String> userStoryTitles) {
		this.userStoryTitles = userStoryTitles;
	}

}
