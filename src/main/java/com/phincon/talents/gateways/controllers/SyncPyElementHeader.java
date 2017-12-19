package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PyElementHeaderForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPyElementHeader {
	private String moduleName = "pyempelementheader";
	@Autowired
	PyElementHeaderForceAdapter pyElementHeaderForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/pyelementheader/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementHeaderForceAdapter.setConfigure(connectedApp,"pyempelementheader");
    	pyElementHeaderForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	
    	
    	return "Pyelementhedaer Pull Completed !";
	}
	
	@RequestMapping(value = "/pyelementheader/init", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerInit(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementHeaderForceAdapter.setConfigure(connectedApp,this.moduleName);
    	pyElementHeaderForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return "Pyelementhedaer Init Completed !";
	}
	
	
}
