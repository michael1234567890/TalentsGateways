package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.OrganizationForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncOrganization {
	private String moduleName = "wssetorganization";
	@Autowired
	OrganizationForceAdapter organizationForceAdapter;

	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService;

	@RequestMapping(value = "/organization/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		organizationForceAdapter.setConfigure(connectedApp, this.moduleName);

		organizationForceAdapter.receive(null, false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());

		return "Organization Pull Completed !";
	}
	
	
	@RequestMapping(value = "/organization/init", method = RequestMethod.GET)
	@ResponseBody
	public String organizationInit(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	organizationForceAdapter.setConfigure(connectedApp,this.moduleName);
    	organizationForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Organization Init Completed !";
	}
}
