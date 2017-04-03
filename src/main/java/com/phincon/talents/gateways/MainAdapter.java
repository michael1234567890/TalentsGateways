package com.phincon.talents.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Component
public class MainAdapter {
	@Autowired
	static 
	EmployeeForceAdapter employeeForceAdapter;
	public static void main(String[] args) {
		
//		EmployeeForceAdapter empAdapter = new EmployeeForceAdapter();
//		empAdapter.receive();
		employeeForceAdapter.receive();
        //return "Hello User!";
	}

}
