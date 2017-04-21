package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.JobTitleForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;

@Controller
@RequestMapping("/sync")
public class SyncJobTitle {
	@Autowired
	JobTitleForceAdapter jobTitleForceAdapter;

	@Autowired
	ConnectedAppService connectedAppService;

	@RequestMapping(value = "/jobtitle/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		jobTitleForceAdapter.setConfigure(connectedApp, "WSSETJOBTITLE__c");
		jobTitleForceAdapter.receive();
		return "Job Title Pull Completed !";
	}

}
