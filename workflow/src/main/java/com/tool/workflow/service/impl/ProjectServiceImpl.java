package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.config.SpringSecurityAuditAwareImpl;
import com.tool.workflow.model.Project;
import com.tool.workflow.model.User;
import com.tool.workflow.repository.ProjectRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.request.ProjectRequest;
import com.tool.workflow.response.ProjectResponse;
import com.tool.workflow.service.ProjectService;
import com.tool.workflow.util.OTPGenerator;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SpringSecurityAuditAwareImpl springSecurityAuditAwareImpl;

	@Override
	public void createProject(ProjectRequest projectRequest) {
		if (projectRepository.existsByProjectName(projectRequest.getProjectName())) {
			throw new RuntimeException("Project Name already exists!");
		}

		String username = springSecurityAuditAwareImpl.getCurrentAuditor().get();

		User user = userRepository.findByUserName(username).orElseThrow();

		Project project = new Project(projectRequest.getProjectName(), projectRequest.getProjectDescription());

		project.setScrumMasterCode(OTPGenerator.generateOTP(6));

		project.setsDETEngineerCode(OTPGenerator.generateOTP(6));

		project.setUser(user);

		project.setProjectStatus("Initiated");

		projectRepository.save(project);
	}

	@Override
	public ProjectResponse getProjectByName(String projectName) {
		Project project = projectRepository.findByProjectName(projectName).orElseThrow();
		return convertToDto(project);
	}

	private ProjectResponse convertToDto(Project project) {

		return new ProjectResponse(project.getProjectName(), project.getProjectDescription(),
				project.getProjectStatus());
	}

	private Project convertToProject(ProjectResponse projectDTO) {

		return projectRepository.findByProjectName(projectDTO.getProjectName()).orElseThrow();

	}
}
