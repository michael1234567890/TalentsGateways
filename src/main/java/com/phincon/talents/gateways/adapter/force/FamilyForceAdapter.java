package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.FamilyService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class FamilyForceAdapter extends ForceAdapter<Family> {

	@Autowired
	FamilyService familyService;
	
	@Autowired
	EmployeeService employeeService;
	
	
	public FamilyForceAdapter() {
		super();
		query = "select Id,Name, Family_Address__c, Family_Birth_Place__c, Family_Blood_Type__c, Family_Date_of_Birth__c, "
				+ "Family_Decease_Date__c, Family_Dependent__c, Family_Last_Education__c, Family_Medical_Status__c, Family_Occupation__c, Family_Phone__c, Family_Relationship__c, Gender__c, Letter_No__c, Marital_Status__c, name__c "
				+ "from HRPERFAMILY__c limit 10";
	}

	@Override
	protected Family convertMapResultToObject(Map<String, Object> mapResult) {
		String name = (String) mapResult.get("Name");
		String employeeExtId = (String) mapResult.get("Name__c");
		System.out.println("EMployee Ext id " + employeeExtId);
		
		String extId = (String) mapResult.get("Id");
		String address = (String) mapResult.get("Family_Address__c");
		String birthPlace = (String) mapResult.get("Family_Birth_Place__c");
		String bloodType = (String) mapResult.get("Family_Blood_Type__c"); 
		String strBirthDate = (String) mapResult.get("Family_Date_of_Birth__c"); 
		String strDeceaseDate = (String) mapResult.get("Family_Decease_Date__c");
		String dependent = (String) mapResult.get("Family_Dependent__c"); 
		String lastEducation = (String) mapResult.get("Family_Last_Education__c"); 
		String medicalStatus = (String) mapResult.get("Family_Medical_Status__c"); 
		String occupation = (String) mapResult.get("Family_Occupation__c"); 
		String phone = (String) mapResult.get("Family_Phone__c"); 
		String relationship = (String) mapResult.get("Family_Relationship__c"); 
		String gender = (String) mapResult.get("Gender__c"); 
		String letterNo = (String) mapResult.get("Letter_No__c"); 
		String maritalStatus = (String) mapResult.get("Marital_Status__c"); 
		Date birthDate = null;
		Date deceaseDate = null;
		if(strDeceaseDate != null)
			deceaseDate = Utils.convertStringToDate(strDeceaseDate);
		if(strBirthDate != null)
			birthDate = Utils.convertStringToDate(strBirthDate);
		

		Family family = new Family();
		family.setAddress(address);
		family.setBirthDate(birthDate);
		family.setBirthPlace(birthPlace);
		family.setBloodType(bloodType);
		family.setDeceaseDate(deceaseDate);
		family.setDependent(dependent);
		family.setExtId(extId);
		family.setGender(gender);
		
		family.setLastEducation(lastEducation);
		family.setRelationship(relationship);
		family.setPhone(phone);
		family.setOccupation(occupation);
		family.setName(name);
		family.setMedicalStatus(medicalStatus);
		family.setMaritalStatus(maritalStatus);
		family.setLetterNo(letterNo);
		family.setEmployeeExtId(employeeExtId);
		
		
		return family;
	}

	@Override
	public void saveListDate(List<Family> listData) {
		for (Family e : listData) {
			System.out.print("Employee : " + e.getExtId());
			// check is id is exist
			Family family = familyService.findByExtId(e.getExtId());

			// if exist doing update
			if (family == null) {
				family = new Family();
			}
			
			family.setAddress(e.getAddress());
			family.setBirthDate(e.getBirthDate());
			family.setBirthPlace(e.getBirthPlace());
			family.setBloodType(e.getBloodType());
			family.setDeceaseDate(e.getDeceaseDate());
			family.setDependent(e.getDependent());
			family.setExtId(e.getExtId());
			family.setGender(e.getGender());
			
			family.setLastEducation(e.getLastEducation());
			family.setRelationship(e.getLastEducation());
			family.setPhone(e.getPhone());
			family.setOccupation(e.getOccupation());
			family.setName(e.getName());
			family.setMedicalStatus(e.getMedicalStatus());
			family.setMaritalStatus(e.getMaritalStatus());
			family.setLetterNo(e.getLetterNo());
			family.setEmployeeExtId(e.getEmployeeExtId());
			
			Employee employee = employeeService.findByExtId(e.getEmployeeExtId());
			if(employee != null){
				family.setEmployee(employee.getId());
				family.setCreatedDate(new Date());
			}
			family.setModifiedDate(new Date());
			familyService.save(family);
			System.out.println("Success Save Family");
		}
	}

}