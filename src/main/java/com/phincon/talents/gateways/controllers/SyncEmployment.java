package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.EmploymentForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncEmployment {
	private String moduleName = "hrempinfo";
	@Autowired
	EmploymentForceAdapter employmentForceAdapter;

	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;

	@RequestMapping(value = "/employment/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull() {
		Date startSync = new Date();
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		employmentForceAdapter.setConfigure(connectedApp, this.moduleName);

		employmentForceAdapter.receive(null,false);
	 	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	
		return "Employment Pull Completed !";
	}
	
	@RequestMapping(value = "/employment/init", method = RequestMethod.GET)
	@ResponseBody
	public String jobtitleInit(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	employmentForceAdapter.setConfigure(connectedApp,this.moduleName);
    	employmentForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return "Employment Init Completed !";
	}

}
