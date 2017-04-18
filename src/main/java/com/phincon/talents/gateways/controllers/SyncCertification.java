package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.CertificationForceAdapter;
import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.services.ConnectedAppService;

@Controller
@RequestMapping("/sync")
public class SyncCertification {
	@Autowired
	CertificationForceAdapter certificationForceAdapter;
	
	@Autowired
	ConnectedAppService connectedAppService;
	
	 @RequestMapping(value = "/certification/pull", method = RequestMethod.GET)
	@ResponseBody
	public String certificationPull(){
		String url = "https://test.salesforce.com";
		String clientId = "3MVG9AJuBE3rTYDg6xqNgcfzIt0yKktBvgS_EGAKJUa3FUAE9Ehfq.kjP.d6sOU8loQaSGVsjT2BUu3CRc4Qt";
    	String clientSecret = "5249588143570196746";
    	String username = "hendra.ramdhan@payroll.dev2.pysandbox1";
    	String password = "bismillah123454iNfyIRLbcB3bLYqVWDzCZ66";
    	
    	ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
    	certificationForceAdapter.setConfigure(connectedApp,"GetAllHRPERCERTIFICATION");
    	certificationForceAdapter.receive();
    	return "Hello User";
	}
	
	@RequestMapping(value = "/certification/send", method = RequestMethod.GET)
	@ResponseBody
	public String certificationSend(){
		ConnectedApp connectedApp = connectedAppService.findByCompany(1L);
		System.out.println(connectedApp.toString());
		
		certificationForceAdapter.setConfigure(connectedApp, "HRPERCERTIFICATION__c");
		certificationForceAdapter.sendNewData();
		return "Hello User!";
	}
}
