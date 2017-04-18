package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.AddressForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;

@Controller
@RequestMapping("/sync")
public class SyncAddress {
	@Autowired
	AddressForceAdapter addressForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@RequestMapping(value = "/address/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	addressForceAdapter.setConfigure(connectedApp,"GetAllHRPERADDRESS");
    	
    	addressForceAdapter.receive();
    	return "Address Pull Completed !";
	}
	
	@RequestMapping(value = "/address/send", method = RequestMethod.GET)
	@ResponseBody
	public String sendAddress(){
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		addressForceAdapter.setConfigure(connectedApp, "InsertUpdateHRPERADDRESS");
		addressForceAdapter.sendNewData();
		return "Address Send Completed !";
	}
}
