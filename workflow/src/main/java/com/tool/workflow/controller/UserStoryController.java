package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.UserStoryRequest;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.UserStoryService;

@RestController
@RequestMapping("/api/admin")
public class UserStoryController {

	@Autowired
	private UserStoryService userStoryService;

	@PostMapping("/create-userstory")
	public ResponseEntity<?> createUserStory(@RequestBody UserStoryRequest userStoryRequest) {
		try {
			userStoryService.createUserStory(userStoryRequest);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("User Story created successfully!"));
	}
}
