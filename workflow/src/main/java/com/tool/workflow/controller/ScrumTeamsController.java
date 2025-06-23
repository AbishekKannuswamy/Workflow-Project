package com.tool.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.workflow.request.ScrumTeamRequest;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.ScrumTeamsService;

@RestController
@RequestMapping("/api/admin")
public class ScrumTeamsController {

	@Autowired
	private ScrumTeamsService scrumTeamsService;

	@PostMapping("/assign-scrum-team")
	public ResponseEntity<?> createAndAssignScrumTeam(@RequestBody ScrumTeamRequest scrumTeamRequest) {

		try {
			scrumTeamsService.createAndAssignScrumTeam(scrumTeamRequest);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Mails are sent successfully!"));

	}

	@GetMapping("/get-scrum-team/{scrumTeamName}")
	public ResponseEntity<?> getScumTeam() {
		return null;
	}
}
