package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.config.SpringSecurityAuditAwareImpl;
import com.tool.workflow.model.Epic;
import com.tool.workflow.model.Project;
import com.tool.workflow.model.User;
import com.tool.workflow.repository.EpicRepository;
import com.tool.workflow.repository.ProjectRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.request.EpicRequest;
import com.tool.workflow.response.EpicResponse;
import com.tool.workflow.service.EpicService;

@Service
public class EpicServiceImpl implements EpicService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SpringSecurityAuditAwareImpl springSecurityAuditAwareImpl;

	@Autowired
	private EpicRepository epicRepository;

	@Override
	public void createEpic(EpicRequest epicRequest) {

		if (epicRepository.existsByTitle(epicRequest.getEpicTitle())) {
			throw new RuntimeException("Epic title already exists!");
		}

		Project project = projectRepository.findByProjectName(epicRequest.getProjectName()).orElseThrow();

		String username = springSecurityAuditAwareImpl.getCurrentAuditor().get();

		User user = userRepository.findByUserName(username).orElseThrow();

		Epic epic = new Epic();
		epic.setTitle(epicRequest.getEpicTitle());
		epic.setDescription(epicRequest.getEpicDescription());
		epic.setProject(project);
		epic.setStatus("Initiated");
		project.setProjectStatus("Ongoing");
		epic.setSprints(epicRequest.getNoOfSprints());

		epicRepository.save(epic);

	}

	@Override
	public EpicResponse getEpicByTitle(String epicTitle) {

		Epic epic = epicRepository.findByTitle(epicTitle).orElseThrow();
		return converToDto(epic);
	}

	private EpicResponse converToDto(Epic epic) {
		EpicResponse epicDTO = new EpicResponse();
		epicDTO.setEpicTitle(epic.getTitle());
		epicDTO.setEpicDescription(epic.getDescription());
		epicDTO.setProjectName(epic.getProject().getProjectName());
		epicDTO.setEpicStatus(epic.getStatus());
		epicDTO.setNoOfSprints(epic.getSprints());
		return epicDTO;
	}
}
