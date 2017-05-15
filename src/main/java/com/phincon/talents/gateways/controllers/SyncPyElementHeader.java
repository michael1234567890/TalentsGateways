package com.phincon.talents.gateways.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PyElementHeaderForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.model.HistorySync;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPyElementHeader {
	private String moduleName = "pyelementheader";
	@Autowired
	PyElementHeaderForceAdapter pyElementHeaderForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/pyelementheader/pull", method = RequestMethod.GET)
	@ResponseBody
	public String pyelementhedaerPull(){
		
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	pyElementHeaderForceAdapter.setConfigure(connectedApp,"pyempelementheader");
    	
    	pyElementHeaderForceAdapter.receive();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	
    	
    	return "Pyelementhedaer Pull Completed !";
	}
	
	
}
