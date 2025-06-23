package com.tool.workflow.service;

import com.tool.workflow.request.ProjectRequest;
import com.tool.workflow.response.ProjectResponse;

public interface ProjectService {

	ProjectResponse getProjectByName(String projectName);

	void createProject(ProjectRequest projectRequest);

}
