package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PyElementDetailForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPyElementDetail {
	private String moduleName = "pyelementdetail";
	
	@Autowired
	PyElementDetailForceAdapter pyElementDetailForceAdapter;

	@Autowired
	HistorySyncService historySyncService ;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@RequestMapping(value = "/pyelementdetail/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementDetailForceAdapter.setConfigure(connectedApp,"pyempelementdetail");
    	
    	pyElementDetailForceAdapter.receive(null,false);

    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Pyempelementdetail Pull Completed !";
	}
	
	
	@RequestMapping(value = "/pyelementdetail/init", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerInit(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementDetailForceAdapter.setConfigure(connectedApp,"pyempelementdetail");
    	pyElementDetailForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Pyempelementdetail Pull Completed !";
	}
	
	
}
