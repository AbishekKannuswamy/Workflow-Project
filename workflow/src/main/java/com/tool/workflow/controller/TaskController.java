package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.TaskRequest;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.TaskService;

@RestController
@RequestMapping("/api/sdet-engineer")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/create-task")
	public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
		try {
			taskService.createTask(taskRequest);
		}

		catch (Exception e) {
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Task created successfully!"));
	}
}
