package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.config.SpringSecurityAuditAwareImpl;
import com.tool.workflow.model.Epic;
import com.tool.workflow.model.Sprint;
import com.tool.workflow.model.User;
import com.tool.workflow.repository.EpicRepository;
import com.tool.workflow.repository.SprintRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.request.SprintRequest;
import com.tool.workflow.service.SprintService;

@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SpringSecurityAuditAwareImpl springSecurityAuditAwareImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private EpicRepository epicRepository;

	@Override
	public void createSprint(SprintRequest sprintRequest) {

		if (sprintRepository.existsBySprintStatus("active")) {
			throw new RuntimeException("There is a active sprint!");
		}

		String username = springSecurityAuditAwareImpl.getCurrentAuditor().get();

		User user = userRepository.findByUserName(username).orElseThrow();

		Epic epic = epicRepository.findByTitle(sprintRequest.getEpicTitle()).orElseThrow();

		Sprint sprint = new Sprint();

		sprint.setSprintGoal(sprintRequest.getSprintGoal());
		sprint.setStartDate(sprintRequest.getStartDate());
		sprint.setEndDate(sprintRequest.getEndDate());
		sprint.setSprintStatus("active");
		sprint.setUser(user);
		sprint.setEpic(epic);

		sprintRepository.save(sprint);

	}

}
