package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.SprintRequest;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.SprintService;

@RestController
@RequestMapping("/api/scrum-master")
public class SprintController {

	@Autowired
	private SprintService sprintService;

	@PostMapping("/create-sprint")
	public ResponseEntity<?> createSprint(@RequestBody SprintRequest sprintRequest) {
		try {
			sprintService.createSprint(sprintRequest);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Sprint created successfully!"));
	}

}
