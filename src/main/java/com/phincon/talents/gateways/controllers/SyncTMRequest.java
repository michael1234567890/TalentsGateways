package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.TMRequestForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncTMRequest {
	private String moduleName = "tmtranrequest";
	
	@Autowired
	TMRequestForceAdapter tmRequestForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/tmrequest/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	tmRequestForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmRequestForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return this.moduleName + " Pull Completed !";
	}
	
	@RequestMapping(value = "/tmrequest/send", method = RequestMethod.GET)
    @ResponseBody
	public String sendRequest() {
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	tmRequestForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmRequestForceAdapter.sendNewData();
        return this.moduleName + " Send Completed !";
    }
	
	
}
