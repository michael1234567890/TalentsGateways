package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class EmployeeForceAdapter extends ForceAdapter<Employee> {

	@Autowired
	EmployeeService employeeService;

	public EmployeeForceAdapter() {
		super();
		query = "select Id,Employee_No__c, First_Name__c,Office_Email__c, Last_Name__c, Date_of_Birth__c,Birthplace__c,Mobile_Phone__c,Middle_Name__c,Religion__c, "
				+ "Blood_Type__c,NRIC_No__c ,NRIC_Date__c,NRIC_Expiration_Date__c, No_NPWP__c,"
				+ "NPWP_Date__c,Nationality__c,Emergency_Contact_Address__c,Emergency_Contact_Name__c,Emergency_Contact_Phone__c,Emergency_Contact_Other_Phone__c,Emergency_Contact_Relationship__c from HRPERINFO__c limit 10";
	}

	@Override
	protected Employee convertMapResultToObject(Map<String, Object> mapResult) {
		String firstName = (String) mapResult.get("First_Name__c");
		String extId = (String) mapResult.get("Id");
		String officeMail = (String) mapResult.get("Office_Email__c");
		String lastName = (String) mapResult.get("Last_Name__c");
		Date dateBirth = null;

		if (mapResult.get("Date_of_Birth__c") != null)
			dateBirth = Utils.convertStringToDate((String) mapResult
					.get("Date_of_Birth__c"));

		String birthPlace = (String) mapResult.get("Birthplace__c");
		String mobilePhone = (String) mapResult.get("Mobile_Phone__c");
		String middleName = (String) mapResult.get("Middle_Name__c");
		String religion = (String) mapResult.get("Religion__c");
		String employeeNo = (String) mapResult.get("Employee_No__c");
		String bloodType = (String) mapResult.get("Blood_Type__c");

//		 
		String nircNo = (String) mapResult.get("NRIC_No__c");
//	   ,
		Date nircDate = null;
		if(mapResult.get("NRIC_Date__c") != null){
			nircDate = Utils.convertStringToDate((String) mapResult
					.get("Date_of_Birth__c"));
		}
		
//		, 
		Date nircExpirationDate = null;
		if(mapResult.get("NRIC_Expiration_Date__c") != null){
			nircExpirationDate = Utils.convertStringToDate((String) mapResult
					.get("NRIC_Expiration_Date__c"));
		}
		
//		,
		String npwpNo = (String) mapResult.get("No_NPWP__c");
//		NPWP_Date__c,
		Date npwpDate = null;
		if(mapResult.get("NPWP_Date__c") != null){
			npwpDate = Utils.convertStringToDate((String) mapResult
					.get("NPWP_Date__c"));
		}
//		,
		String nationality = (String) mapResult.get("Nationality__c");
//		Emergency_Contact_Address__c,
//		Emergency_Contact_Name__c,
//		Emergency_Contact_Phone__c,
//		Emergency_Contact_Other_Phone__c,
//		Emergency_Contact_Relationship__c
		
		
		Employee employee = new Employee();
		employee.setExtId(extId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setOfficeMail(officeMail);
		employee.setBirthDate(dateBirth);
		employee.setBirthPlace(birthPlace);
		employee.setMobilePhone(mobilePhone);
		employee.setBloodType(bloodType);
		employee.setEmployeeNo(employeeNo);
		employee.setReligion(religion);
		employee.setMiddleName(middleName);
		return employee;
	}

	@Override
	public void saveListDate(List<Employee> listData) {
		for (Employee e : listData) {
			System.out.print("Employee : " + e.getExtId());
			// check is id is exist
			Employee empDb = employeeService.findByExtId(e.getExtId());

			// if exist doing update
			if (empDb == null) {
				empDb = new Employee();
				empDb.setCreatedDate(new Date());
			}
			empDb.setModifiedDate(new Date());
			empDb.setExtId(e.getExtId());
			empDb.setFirstName(e.getFirstName());

			empDb.setLastName(e.getLastName());
			empDb.setOfficeMail(e.getOfficeMail());
			empDb.setBirthDate(e.getBirthDate());
			empDb.setBirthPlace(e.getBirthPlace());
			empDb.setMobilePhone(e.getMobilePhone());
			empDb.setBloodType(e.getBloodType());
			empDb.setEmployeeNo(e.getEmployeeNo());
			empDb.setReligion(e.getReligion());
			empDb.setMiddleName(e.getMiddleName());
			employeeService.save(empDb);
			System.out.println("Success Save Employee");
		}
	}

}
