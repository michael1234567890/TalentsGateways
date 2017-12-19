package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.GroupForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncGroup {
	
	private String moduleName = "Group";

	@Autowired
	GroupForceAdapter groupForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService;
	
//	@RequestMapping(value = "/group/pull", method = RequestMethod.GET)
//	@ResponseBody
//	public String groupPull(){
//		
//		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
//		groupForceAdapter.setConfigure(connectedApp, this.moduleName);
//		
//		groupForceAdapter.receive();
//		historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
//		
//		return "Group Pull Completed";
//	}
	
	@RequestMapping(value = "/group/init", method = RequestMethod.GET)
	@ResponseBody
	public String groupInit() {
		Date startSync = new Date();
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		groupForceAdapter.setConfigure(connectedApp, this.moduleName);
		groupForceAdapter.receive(null,false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany(),startSync);
		return "Group Init Completed !";
	}
	
	
}
