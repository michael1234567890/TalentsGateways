package com.phincon.talents.gateways.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PyElementDetailYearlyForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPyElementDetailYearly {
	private String moduleName = "payslipyearly";
	@Autowired
	PyElementDetailYearlyForceAdapter pyElementDetailYearlyForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/pyelementdetailyearly/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		Date startSync = new Date();
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementDetailYearlyForceAdapter.setConfigure(connectedApp,this.moduleName);
    	pyElementDetailYearlyForceAdapter.receive(null,false);
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany(),startSync);
    	
    	
    	return "PyElementDetailYearly Pull Completed !";
	}
	
	
}
