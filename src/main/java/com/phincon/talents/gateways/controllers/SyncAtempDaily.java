package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.AtempDailyForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncAtempDaily {

	private String moduleName = "atempdaily";
	
	@Autowired
	AtempDailyForceAdapter atempDailyForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService;
	
	@RequestMapping(value = "/atempdaily/init", method = RequestMethod.GET)
	@ResponseBody
	public String atempDailyInit(){
		Date startSync = new Date();
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		atempDailyForceAdapter.setConfigure(connectedApp, this.moduleName);
		atempDailyForceAdapter.initRetrieve();
		historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(), startSync);
		
		return "AtEmpDaily Init Completed";
	}
}
