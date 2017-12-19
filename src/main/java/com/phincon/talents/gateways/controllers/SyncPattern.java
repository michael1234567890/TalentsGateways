package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PatternForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPattern {
	
	private String moduleName = "Pattern";
	
	@Autowired
	PatternForceAdapter patternForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService;
	
//	@RequestMapping(value = "/pattern/pull", method = RequestMethod.GET)
//	@ResponseBody
//	public String patternPull(){
//		
//		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
//		patternForceAdapter.setConfigure(connectedApp, this.moduleName);
//		
//		patternForceAdapter.receive();
//		historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
//		
//		return "Pattern Pull Completed";
//	}
	
	@RequestMapping(value = "/pattern/init", method = RequestMethod.GET)
	@ResponseBody
	public String patternInit() {
		Date startSync = new Date();
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		patternForceAdapter.setConfigure(connectedApp, this.moduleName);
		patternForceAdapter.receive(null,false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany(),startSync);
		return "Pattern Init Completed !";
	}
}
