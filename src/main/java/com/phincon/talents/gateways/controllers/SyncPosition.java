package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PositionForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;

@Controller
@RequestMapping("/sync")
public class SyncPosition {
	
	@Autowired
	PositionForceAdapter positionForceAdaptor;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@RequestMapping(value = "/position/pull", method = RequestMethod.GET)
	@ResponseBody
	public String positionPull(){
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		positionForceAdaptor.setConfigure(connectedApp, "WSSETPOSITION__c");
		positionForceAdaptor.receive();
		
		return "Position Pull Completed!";
	}
}
