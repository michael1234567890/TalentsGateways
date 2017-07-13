package com.phincon.talents.gateways.controllers;

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
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	tmRequestForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmRequestForceAdapter.receive();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return this.moduleName + " Pull Completed !";
	}
	
	@RequestMapping(value = "/tmrequest/send", method = RequestMethod.GET)
    @ResponseBody
	public String sendRequest() {
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	System.out.println(connectedApp.toString());
    	
    	tmRequestForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmRequestForceAdapter.sendNewData();
        return "Family Send Completed !";
    }
	
	
}
