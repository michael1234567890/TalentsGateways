package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.EmployeePayrollForceAdapeter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;
import com.phincon.talents.gateways.services.HistorySyncService;

@Controller
@RequestMapping("/sync")
public class SyncEmployeePayroll {
	private String moduleName = "PYEMPPAYROLL__c";
	
	@Autowired
	EmployeePayrollForceAdapeter empPayrollForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	@Autowired
	HistorySyncService historySyncService;
	
//	@RequestMapping(value = "/pyemppayroll/pull", method = RequestMethod.GET)
//	@ResponseBody
//	public String empPayrollPull(){
//		
//		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
//		empPayrollForceAdapter.setConfigure(connectedApp, moduleName);
//		
//		//empPayrollForceAdapter.receive();
//		historySyncService.createOrUpdateSync(this.moduleName, connectedApp.getCompany());
//		
//		return "PyEmpPayroll Pull Completed";
//	}
	
	@RequestMapping(value = "/employeepayroll/init", method = RequestMethod.GET)
	@ResponseBody
	public String employeePayrollInit() {

		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		empPayrollForceAdapter.setConfigure(connectedApp, this.moduleName);
		empPayrollForceAdapter.receive(null,false);
		historySyncService.createOrUpdateSync(this.moduleName,
				connectedApp.getCompany());
		return "Employee Payroll Init Completed !";
	}
	
}
