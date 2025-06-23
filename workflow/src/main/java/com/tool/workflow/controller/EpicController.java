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

import com.tool.workflow.request.EpicRequest;
import com.tool.workflow.response.EpicResponse;
import com.tool.workflow.security.response.MessageResponse;
import com.tool.workflow.service.EpicService;

@RestController
@RequestMapping("/api/admin")
public class EpicController {

	@Autowired
	private EpicService epicService;

	@PostMapping("/create-epic")
	public ResponseEntity<?> createEpic(@RequestBody EpicRequest epicRequest) {

		try {
			epicService.createEpic(epicRequest);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<MessageResponse>(HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(new MessageResponse("Epic created successfully!"));
	}

	@GetMapping("/epic/{epicTitle}")
	public ResponseEntity<EpicResponse> getEpic(@PathVariable String epicTitle) {

		try {
			EpicResponse epicResponse = epicService.getEpicByTitle(epicTitle);
			return new ResponseEntity<EpicResponse>(epicResponse, HttpStatus.FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<EpicResponse>(HttpStatus.NOT_FOUND);
		}

	}

}
