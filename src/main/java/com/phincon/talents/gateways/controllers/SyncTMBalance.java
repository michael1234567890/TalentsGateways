package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.TMBalanceForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncTMBalance {
	private String moduleName = "TMEMPBALANCE__c";
	
	@Autowired
	TMBalanceForceAdapter tmBalanceForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/tmbalance/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	tmBalanceForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmBalanceForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return this.moduleName + " Pull Completed !";
	}
	

	@RequestMapping(value = "/tmbalance/send", method = RequestMethod.GET)
    @ResponseBody
	public String sendRequest() {
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	System.out.println(connectedApp.toString());
    	tmBalanceForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmBalanceForceAdapter.sendNewData();
        return this.moduleName + " Send Completed !";
    }
	
	@RequestMapping(value = "/tmbalance/init", method = RequestMethod.GET)
	@ResponseBody
	public String tmbalanceInit(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	tmBalanceForceAdapter.setConfigure(connectedApp,this.moduleName);
    	tmBalanceForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	return this.moduleName + " Init Completed !";
	}
	
	
	
}
