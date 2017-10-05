package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.ShiftForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncShift {
	
	private String moduleName = "Shift";
	
	@Autowired
	ShiftForceAdapter shiftForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService;
	
//	@RequestMapping(value = "/shift/pull", method = RequestMethod.GET)
//	@ResponseBody
//	public String shiftPull(){
//		
//		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
//		shiftForceAdapter.setConfigure(connectedApp, this.moduleName);
//		
//		shiftForceAdapter.receive();
//		historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
//		
//		return "Shift Pull Completed";
//	}
	
	@RequestMapping(value = "/shift/init", method = RequestMethod.GET)
	@ResponseBody
	public String sihftInit() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		shiftForceAdapter.setConfigure(connectedApp, this.moduleName);
		shiftForceAdapter.receive(null,false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());
		return "Shift Init Completed !";
	}
}
