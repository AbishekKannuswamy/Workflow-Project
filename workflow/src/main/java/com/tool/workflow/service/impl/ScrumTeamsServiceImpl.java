package com.tool.workflow.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.model.Project;
import com.tool.workflow.model.ProjectMembers;
import com.tool.workflow.model.ScrumTeams;
import com.tool.workflow.repository.ProjectMembersRepository;
import com.tool.workflow.repository.ProjectRepository;
import com.tool.workflow.repository.ScrumTeamsRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.request.ScrumTeamRequest;
import com.tool.workflow.service.ScrumTeamsService;
import com.tool.workflow.util.EmailService;

@Service
public class ScrumTeamsServiceImpl implements ScrumTeamsService {

	@Autowired
	private ScrumTeamsRepository scrumTeamsRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectMembersRepository projectMembersRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void createAndAssignScrumTeam(ScrumTeamRequest scrumTeamRequest) {

		Project project = projectRepository.findByProjectName(scrumTeamRequest.getProjectName()).orElseThrow();

		ScrumTeams scrumTeam = new ScrumTeams(project, scrumTeamRequest.getScrumTeamName());

		scrumTeamsRepository.save(scrumTeam);

		emailService.sendProjectAssignmentEmail(scrumTeamRequest.getEmailAndRole(), project.getScrumMasterCode(),
				project.getsDETEngineerCode(), project.getProjectName(), project.getProjectDescription(),
				project.getUser().getUserName(), project.getUser().getEmail());

		Map<String, String> emailAndRole = scrumTeamRequest.getEmailAndRole();
		for (String email : emailAndRole.keySet()) {

			if (userRepository.existsByEmail(email)) {
				throw new RuntimeException(email + " already exists!");
			}

			ProjectMembers projectMembers = new ProjectMembers();
			projectMembers.setEmail(email);
			projectMembers.setRole(emailAndRole.get(email));
			projectMembers.setScrumTeam(scrumTeam);
			projectMembersRepository.save(projectMembers);
		}

	}

}
