package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.model.Epic;
import com.tool.workflow.model.UserStories;
import com.tool.workflow.repository.EpicRepository;
import com.tool.workflow.repository.UserStoryRepository;
import com.tool.workflow.request.UserStoryRequest;
import com.tool.workflow.service.UserStoryService;

@Service
public class UserStoryServiceImpl implements UserStoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;

	@Autowired
	private EpicRepository epicRepository;

	@Override
	public void createUserStory(UserStoryRequest userStoryRequest) {

		if (userStoryRepository.existsByUserStoryTitle(userStoryRequest.getUserStoryTitle())) {
			throw new RuntimeException("User Story already exists!");
		}

		UserStories userStory = new UserStories();

		userStory.setUserStoryTitle(userStoryRequest.getUserStoryTitle());
		userStory.setUserStoryDescription(userStoryRequest.getUserStoryDescription());
		userStory.setUserStoryAcceptanceCriteria(userStoryRequest.getUserStoryAcceptanceCriteria());
		userStory.setUserStoryPriority(userStoryRequest.getUserStoryPriority());
		userStory.setUserStoryStatus("Initiated");

		Epic epic = epicRepository.findByTitle(userStoryRequest.getEpicTitle()).orElseThrow();
		epic.setStatus("Ongoing");
		userStory.setEpic(epic);

		userStoryRepository.save(userStory);

	}
}
