package com.phincon.talents.gateways.controllers;

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
		
    	
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	assignmentForceAdapter.setConfigure(connectedApp,this.moduleName);
    	assignmentForceAdapter.receive();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	
    	return "Successfully Pull Assignment";
	}
	
}
