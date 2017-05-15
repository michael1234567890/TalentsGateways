package com.phincon.talents.gateways.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.EmployeeForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncEmployee {
	private String moduleName = "HRPERINFO";
	@Autowired
	EmployeeForceAdapter employeeForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;

	@Autowired
	HistorySyncService historySyncService ;
	
	
    @RequestMapping(value="/employee/pull", method = RequestMethod.GET)
    @ResponseBody
	public String employeePull() {

    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	employeeForceAdapter.setConfigure(connectedApp,this.moduleName);
    	employeeForceAdapter.receive();
    	historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
        return "Employee Pull Completed !";
    }
    
    @RequestMapping(value = "/employee/send", method = RequestMethod.GET)
    @ResponseBody
	public String sendFamilies() {
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	employeeForceAdapter.setConfigure(connectedApp,"InsertUpdateHRPERINFO");
    	employeeForceAdapter.sendNewData();
        return "Employee Send Completed !";
    }
    

}
