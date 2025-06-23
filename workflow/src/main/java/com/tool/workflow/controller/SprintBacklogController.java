package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.SprintBacklogRequest;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.SprintBacklogService;

@RestController
@RequestMapping("/api/scrum-master")
public class SprintBacklogController {

	@Autowired
	private SprintBacklogService sprintBacklogService;

	@PostMapping("/create-sprint-backlog")
	public ResponseEntity<?> createSprintBacklog(@RequestBody SprintBacklogRequest sprintBacklogRequest) {
		try {
			sprintBacklogService.createSprintBacklog(sprintBacklogRequest);
		}

		catch (Exception e) {
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Sprint Backlog created successfully!"));
	}

}
