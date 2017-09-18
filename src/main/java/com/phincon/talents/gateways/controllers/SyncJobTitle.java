package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.JobTitleForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncJobTitle {
	private String moduleName = "WSSETJOBTITLE__c";
	
	@Autowired
	JobTitleForceAdapter jobTitleForceAdapter;
	@Autowired
	HistorySyncService historySyncService;

	@Autowired
	ConnectedAppService connectedAppService;

	@RequestMapping(value = "/jobtitle/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		jobTitleForceAdapter.setConfigure(connectedApp, this.moduleName);
		jobTitleForceAdapter.receive(null,false);
		return "Job Title Pull Completed !";
	}
	
	@RequestMapping(value = "/jobtitle/init", method = RequestMethod.GET)
	@ResponseBody
	public String jobtitleInit(){
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	jobTitleForceAdapter.setConfigure(connectedApp,this.moduleName);
    	jobTitleForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Job Title Init Completed !";
	}

}
