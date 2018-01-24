package com.phincon.talents.gateways.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.Users;
import com.phincon.talents.gateways.repository.EmployeeRepository;
import com.phincon.talents.gateways.repository.EmploymentRepository;
import com.phincon.talents.gateways.repository.UsersRepository;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.UsersService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Service
public class UsersAdapter {

	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	EmployeeService employeeService;
		 
	@Autowired
	EmployeeRepository employeeRepository;


	@Autowired
	EmploymentService employmentService;
		 
	@Autowired
	EmploymentRepository employmentRepository;

	
	
	public void automaticUserCreation() {		
		List<Employee> listEmployee = employeeService.findActiveEmployeeNoUser();
		for (Employee employee : listEmployee) {
			
			Long empID = employee.getId();
			String strUserName = employee.getOfficeMail();
			String strEmail = employee.getOfficeMail();
			String strFirstName = employee.getFirstName();
			String strLastName = employee.getLastName();			
			Long companyID = employee.getCompany();
			String strFullName = employee.getFirstName() + " " + employee.getLastName();			
            String strGuid = UUID.randomUUID().toString();
            Date birthDate = employee.getBirthDate();
			 
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            String strBirthDate = sdf.format(birthDate);
            
            List<Employment> listEmployment = employmentRepository.findByEmployee(empID);
            for (Employment employment : listEmployment) {
            	
            	String strEmployeeNumber = employment.getName();
            	String strPassword = strEmployeeNumber + strBirthDate;            	
            	
                PasswordEncoder passwordEncoder;            
                passwordEncoder = new BCryptPasswordEncoder ();
                String strEncryptedPass = passwordEncoder.encode(strPassword);
                
            	Users user = new Users();            	 
            	user.setUsername(strUserName);
            	user.setUuid_str(strGuid);
            	user.setEmail(strEmail);
            	user.setPassword(strEncryptedPass);
            	user.setFirst_name(strFirstName);
            	user.setLast_name(strLastName);
            	user.setFull_name(strFullName);
            	user.setActivated(true);
            	user.setEmployee_id(empID);
            	user.setCompany_id(companyID);            	
            	user.setIs_admin(false);
            	user.setIs_hr(false);
            	user.setIs_leader(false);
            	user.setIs_change_password(false);
            	usersService.save(user);
            	//System.out.println(employee.getOfficeMail());
            	//System.out.println(employment.getName());
            }			
		}
	}
	 
}