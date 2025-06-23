package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.model.Sprint;
import com.tool.workflow.model.SprintBacklog;
import com.tool.workflow.model.UserStories;
import com.tool.workflow.repository.EpicRepository;
import com.tool.workflow.repository.SprintBacklogRepository;
import com.tool.workflow.repository.SprintRepository;
import com.tool.workflow.repository.UserStoryRepository;
import com.tool.workflow.request.SprintBacklogRequest;
import com.tool.workflow.service.SprintBacklogService;

@Service
public class SprintBacklogServiceImpl implements SprintBacklogService {

	@Autowired
	private SprintBacklogRepository sprintBacklogRepository;

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private EpicRepository epicRepository;

	@Autowired
	private UserStoryRepository userStoryRepository;

	@Override
	public void createSprintBacklog(SprintBacklogRequest sprintBacklogRequest) {

		Sprint sprint = sprintRepository.findBySprintStatus("active").orElseThrow();
		SprintBacklog sprintBacklog = new SprintBacklog();

		sprintBacklog.setSprint(sprint);

		for (String userStoryTitle : sprintBacklogRequest.getUserStoryTitles()) {
			UserStories userStory = userStoryRepository.findByUserStoryTitle(userStoryTitle).orElseThrow();
			if (userStory.getSprintBacklog() != null) {
				userStory.setSprintBacklog(sprintBacklog);
			} else {
				throw new RuntimeException("User story is already taken by other team!");
			}

		}

		sprintBacklogRepository.save(sprintBacklog);

	}

}
