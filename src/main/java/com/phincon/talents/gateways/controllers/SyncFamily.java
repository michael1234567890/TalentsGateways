package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.FamilyForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncFamily {
	private String moduleName = "HRPERFAMILY";
	@Autowired
	FamilyForceAdapter familyAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	

	@Autowired
	HistorySyncService historySyncService ;
	
	@RequestMapping(value = "/family/pull", method = RequestMethod.GET)
    @ResponseBody
	public String getFamilies() {

    	
    	//familyAdapter.setConfigure(url,clientId, cleintSecret, username, password);
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	familyAdapter.setConfigure(connectedApp,this.moduleName);
    	
    	familyAdapter.receive(null,false);

	 	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
        return "Family pull completed !";
    }
    
    @RequestMapping(value = "/family/send", method = RequestMethod.GET)
    @ResponseBody
	public String sendFamilies() {
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	familyAdapter.setConfigure(connectedApp,this.moduleName);
    	familyAdapter.sendNewData();
        return "Family Send Completed !";
    }
    

    
    @RequestMapping(value = "/family/update", method = RequestMethod.GET)
    @ResponseBody
    public String updateFamilies(){
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	System.out.println(connectedApp.toString());
    	
    	familyAdapter.setConfigure(connectedApp,this.moduleName);
    	familyAdapter.sendUpdatedData();
    	return "Family Update Completed!";
    }
    
    
    @RequestMapping(value = "/family/init", method = RequestMethod.GET)
	@ResponseBody
	public String familyInit(){
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	familyAdapter.setConfigure(connectedApp,this.moduleName);
    	familyAdapter.initRetrieve();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
    	return "Family Init Completed !";
	}
    
    
}
