package com.tool.workflow.service;

import com.tool.workflow.request.EpicRequest;
import com.tool.workflow.response.EpicResponse;

public interface EpicService {

	void createEpic(EpicRequest epicRequest);

	EpicResponse getEpicByTitle(String epicTitle);

}
