package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.CertificationForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncCertification {
	private String moduleName = "HRPERCERTIFICATION";
	@Autowired
	CertificationForceAdapter certificationForceAdapter;

	@Autowired
	HistorySyncService historySyncService ;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	 @RequestMapping(value = "/certification/pull", method = RequestMethod.GET)
	@ResponseBody
	public String certificationPull(){

    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	certificationForceAdapter.setConfigure(connectedApp,this.moduleName);
    	certificationForceAdapter.receive(null,false);

	 	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Certification Pull Completed!";
	}
	
	@RequestMapping(value = "/certification/send", method = RequestMethod.GET)
	@ResponseBody
	public String certificationSend(){
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		certificationForceAdapter.setConfigure(connectedApp, this.moduleName);
		certificationForceAdapter.sendNewData();
		return "Certification Send Completed!";
	}
	
	@RequestMapping(value = "/certification/init", method = RequestMethod.GET)
	@ResponseBody
	public String certificationInit(){
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	certificationForceAdapter.setConfigure(connectedApp,this.moduleName);
    	certificationForceAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Certification Init Completed !";
	}
}
