package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.ProjectRequest;
import com.tool.workflow.response.ProjectResponse;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.ProjectService;

@RestController
@RequestMapping("/api/admin")

public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("/create-project")
	public ResponseEntity<MessageResponse> createProject(@RequestBody ProjectRequest projectRequest) {

		try {
			projectService.createProject(projectRequest);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Project created successfully!"));
	}

	@GetMapping("/project/{projectName}")
	public ResponseEntity<ProjectResponse> getProject(@PathVariable String projectName) {

		try {
			ProjectResponse projectResponse = projectService.getProjectByName(projectName);
			return new ResponseEntity<ProjectResponse>(projectResponse, HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ProjectResponse>(HttpStatus.NOT_FOUND);
		}

	}

}
