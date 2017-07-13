package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PyElementDetailGroupingForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPyElementDetailGrouping {
	private String moduleName = "payslip";
	
	@Autowired
	PyElementDetailGroupingForceAdapter pyElementDetailForceAdapter;

	@Autowired
	HistorySyncService historySyncService ;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@RequestMapping(value = "/pyelementdetailgrouping/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementDetailForceAdapter.setConfigure(connectedApp,"payslip");
    	
    	pyElementDetailForceAdapter.receive();

    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Pyempelementdetailgrouping Pull Completed !";
	}
	
	
	
	
}
