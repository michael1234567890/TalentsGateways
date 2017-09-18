package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.PositionForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncPosition {
	private String moduleName = "wssetposition";
	@Autowired
	PositionForceAdapter positionForceAdaptor;

	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService;

	@RequestMapping(value = "/position/pull", method = RequestMethod.GET)
	@ResponseBody
	public String positionPull() {
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		positionForceAdaptor.setConfigure(connectedApp, this.moduleName);
		positionForceAdaptor.receive(null, false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());

		return "Position Pull Completed!";
	}

	@RequestMapping(value = "/position/init", method = RequestMethod.GET)
	@ResponseBody
	public String positionInit() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		positionForceAdaptor.setConfigure(connectedApp, this.moduleName);
		positionForceAdaptor.initRetrieve();
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());
		return "Position Init Completed !";
	}
}
