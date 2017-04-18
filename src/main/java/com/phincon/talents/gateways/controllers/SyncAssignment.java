package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.AssignmentForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;

@Controller
@RequestMapping("/sync")
public class SyncAssignment {
	@Autowired
	AssignmentForceAdapter assignmentForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	 @RequestMapping(value = "/assignment/pull", method = RequestMethod.GET)
	@ResponseBody
	public String certificationPull(){
		
    	
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	assignmentForceAdapter.setConfigure(connectedApp,"GetAllHREMPASSIGNMENT");
    	assignmentForceAdapter.receive();
    	return "Successfully Pull Assignment";
	}
	
}
