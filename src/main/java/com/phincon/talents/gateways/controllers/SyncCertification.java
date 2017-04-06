package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.force.CertificationForceAdapter;

@Controller
@RequestMapping("/syncCertification")
public class SyncCertification {
	@Autowired
	CertificationForceAdapter certificationForceAdapter;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String sayHello(){
		String url = "https://test.salesforce.com";
    	String clientId = "3MVG9AJuBE3rTYDg6xqNgcfzIt0yKktBvgS_EGAKJUa3FUAE9Ehfq.kjP.d6sOU8loQaSGVsjT2BUu3CRc4Qt";
    	String cleintSecret = "5249588143570196746";
    	String username = "hendra.ramdhan@payroll.dev2.pysandbox1";
    	String password = "bismillah123454iNfyIRLbcB3bLYqVWDzCZ66";
    	
    	certificationForceAdapter.setConfigure(url,clientId, cleintSecret, username, password);
    	certificationForceAdapter.receive();
    	return "Hello User";
	}
}