package com.phincon.talents.gateways;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.phincon.talents.gateways.adapter.force.EmployeeForceAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Component
public class MainAdapter {
//	@Autowired
//	static 
//	EmployeeForceAdapter employeeForceAdapter;
	public static void main(String[] args) {
		
		EmployeeForceAdapter employeeForceAdapter = new EmployeeForceAdapter();
//		empAdapter.receive();
		// employeeForceAdapter.receive();
		String url = "https://test.salesforce.com";
    	String clientId = "3MVG9AJuBE3rTYDg6xqNgcfzIt0yKktBvgS_EGAKJUa3FUAE9Ehfq.kjP.d6sOU8loQaSGVsjT2BUu3CRc4Qt";
    	String cleintSecret = "5249588143570196746";
    	String username = "hendra.ramdhan@payroll.dev2.pysandbox1";
    	String password = "bismillah123454iNfyIRLbcB3bLYqVWDzCZ66";
    	
    	employeeForceAdapter.setConfigure(url,clientId, cleintSecret, username, password);
    	employeeForceAdapter.sendNewData();;
    	
        //return "Hello User!";
	}

}
