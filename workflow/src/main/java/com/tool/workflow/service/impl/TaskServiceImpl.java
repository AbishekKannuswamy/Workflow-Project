package com.tool.workflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tool.workflow.config.SpringSecurityAuditAwareImpl;
import com.tool.workflow.model.Task;
import com.tool.workflow.model.User;
import com.tool.workflow.model.UserStories;
import com.tool.workflow.repository.TaskRepository;
import com.tool.workflow.repository.UserRepository;
import com.tool.workflow.repository.UserStoryRepository;
import com.tool.workflow.request.TaskRequest;
import com.tool.workflow.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private SpringSecurityAuditAwareImpl springSecurityAuditAwareImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserStoryRepository userStoryRepository;

	@Override
	public void createTask(TaskRequest taskRequest) {

		String username = springSecurityAuditAwareImpl.getCurrentAuditor().get();

		User user = userRepository.findByUserName(username).orElseThrow();

		UserStories userStory = userStoryRepository.findByUserStoryTitle(taskRequest.getUserStoryTitle()).orElseThrow();

		Task task = new Task();
		task.setTaskTitle(taskRequest.getTaskTitle());
		task.setTaskDescription(taskRequest.getTaskDescription());
		task.setPriority(taskRequest.getPriority());
		task.setStatus("todo");
		task.setDueDate(taskRequest.getDueDate());
		task.setUser(user);
		task.setUserStory(userStory);
		taskRepository.save(task);

	}
}
