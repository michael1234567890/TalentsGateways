package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.AddressForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncAddress {
	private String moduleName = "HRPERADDRESS";
	@Autowired
	AddressForceAdapter addressForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/address/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	addressForceAdapter.setConfigure(connectedApp,this.moduleName);
    	
    	addressForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
       
    	return "Address Pull Completed !";
	}
	
	@RequestMapping(value = "/address/send", method = RequestMethod.GET)
	@ResponseBody
	public String sendAddress(){
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		addressForceAdapter.setConfigure(connectedApp, this.moduleName);
		addressForceAdapter.sendNewData();
		return "Address Send Completed !";
	}
	
	@RequestMapping(value = "/address/init", method = RequestMethod.GET)
	@ResponseBody
	public String addressInit(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	addressForceAdapter.setConfigure(connectedApp,this.moduleName);
    	addressForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
       
    	return "Address Init Completed !";
	}
}
