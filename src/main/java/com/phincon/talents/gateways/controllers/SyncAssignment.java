package com.phincon.talents.gateways.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.phincon.talents.gateways.adapter.force.AssignmentForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncAssignment {
	private String moduleName = "HREMPASSIGNMENT";
	@Autowired
	AssignmentForceAdapter assignmentForceAdapter;

	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/assignment/pull", method = RequestMethod.GET)
	@ResponseBody
	public String certificationPull(){
		 Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	assignmentForceAdapter.setConfigure(connectedApp,this.moduleName);
    	assignmentForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return "Successfully Pull Assignment";
	}
	 
	 @RequestMapping(value = "/assignment/init", method = RequestMethod.GET)
		@ResponseBody
		public String assignmentInit(){
		 	Date startSync = new Date();
	    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
	    	assignmentForceAdapter.setConfigure(connectedApp,this.moduleName);
	    	assignmentForceAdapter.initRetrieve();
	    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
	    	return "Assignment Init Completed !";
		}
	
}
