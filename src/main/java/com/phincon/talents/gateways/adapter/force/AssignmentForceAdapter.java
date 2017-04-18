package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Assignment;
import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;

@Service
public class AssignmentForceAdapter extends ForceAdapter<Assignment> {

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	EmployeeService employeeService;

	public AssignmentForceAdapter() {
		super();
		// query =
		// "select Id,Name, Family_Address__c, Family_Birth_Place__c, Family_Blood_Type__c, Family_Date_of_Birth__c, "
		// +
		// "Family_Decease_Date__c, Family_Dependent__c, Family_Last_Education__c, Family_Medical_Status__c, Family_Occupation__c, Family_Phone__c, Family_Relationship__c, Gender__c, Letter_No__c, Marital_Status__c, name__c "
		// + "from HRPERFAMILY__c limit 10";
		query = "GetAllHREMPASSIGNMENT";
	}

	@Override
	protected Assignment convertMapResultToObject(Map<String, Object> mapResult) {
		String name = (String) mapResult.get("Name");
		String status = (String) mapResult.get("Employee_Status__c");
		String type = (String) mapResult.get("Employee_Type__c");
		String employeeExtId = (String) mapResult.get("Employee_No__c");

		String extId = (String) mapResult.get("Id");

		Assignment assignment = new Assignment();
		assignment.setEmployeeExtId(employeeExtId);
		assignment.setType(type);
		assignment.setStatus(status);
		assignment.setExtId(extId);
		assignment.setName(name);

		return assignment;
	}

	@Override
	public void saveListDate(List<Assignment> listData) {
		for (Assignment e : listData) {
			Assignment assignment = assignmentService.findByExtId(e.getExtId());

			// if exist doing update
			if (assignment == null) {
				assignment = new Assignment();
			}
			assignment.setEmployeeExtId(e.getEmployeeExtId());
			assignment.setCategory(e.getCategory());
			assignment.setStatus(e.getStatus());
			assignment.setType(e.getType());
			assignment.setExtId(e.getExtId());

			Employee employee = employeeService.findByExtId(e
					.getEmployeeExtId());
			if (employee != null) {
				assignment.setEmployee(employee.getId());

			}
			
			assignment.setCreatedDate(new Date());
			assignment.setModifiedDate(new Date());
			assignmentService.save(assignment);
			System.out.println("Success Save Assignment");
		}
	}



}
