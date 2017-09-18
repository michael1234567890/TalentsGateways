package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.GradeForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncGrade {
	private String moduleName = "WSSETGRADE";
	@Autowired
	GradeForceAdapter gradeForceAdapter;

	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService;

	@RequestMapping(value = "/grade/pull", method = RequestMethod.GET)
	@ResponseBody
	public String addressPull() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		gradeForceAdapter.setConfigure(connectedApp, this.moduleName);

		gradeForceAdapter.receive(null, false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());

		return "Grade Pull Completed !";
	}
	
	@RequestMapping(value = "/grade/init", method = RequestMethod.GET)
	@ResponseBody
	public String gradeInit(){
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	gradeForceAdapter.setConfigure(connectedApp,this.moduleName);
    	gradeForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Grade Init Completed !";
	}
}
