package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Assignment;
import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.JobTitle;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.JobTitleService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class AssignmentForceAdapter extends ForceAdapter<Assignment> {

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	EmploymentService employmentService;

	@Autowired
	JobTitleService jobTitleService;

	public AssignmentForceAdapter() {
		super();
		// query =
		// "select Id,Name, Family_Address__c, Family_Birth_Place__c, Family_Blood_Type__c, Family_Date_of_Birth__c, "
		// +
		// "Family_Decease_Date__c, Family_Dependent__c, Family_Last_Education__c, Family_Medical_Status__c, Family_Occupation__c, Family_Phone__c, Family_Relationship__c, Gender__c, Letter_No__c, Marital_Status__c, name__c "
		// + "from HRPERFAMILY__c limit 10";
		query = "HREMPASSIGNMENT";
	}

	@Override
	protected Assignment convertMapResultToObject(Map<String, Object> mapResult) {
		String name = (String) mapResult.get("Name");
		String status = (String) mapResult.get("Employee_Status__c");
		String type = (String) mapResult.get("Employee_Type__c");
		String employmentExtId = (String) mapResult.get("Employee_No__c");
		String extId = (String) mapResult.get("Id");
		String jobTitleExtId = (String) mapResult.get("Job_Title_Name__c");
		String category = (String) mapResult.get("Category__c");
		String description = (String) mapResult.get("Description__c");
		String strEmploymentSupervisorExtId = (String) mapResult
				.get("Direct_Supervisor__c"); // employment Supervisor
		String divisi = (String) mapResult.get("Divisi__c");
		String employeeStatus = (String) mapResult.get("Employee_Status__c");
		String employeeType = (String) mapResult.get("Employee_Type__c");
		String strEndDate = (String) mapResult.get("End_Date__c");
		Date endDate = null;
		if (strEndDate != null)
			endDate = Utils.convertStringToDate(strEndDate);

		String strLetterDate = (String) mapResult.get("Letter_Date__c");
		Date letterDate = null;
		if (strLetterDate != null)
			letterDate = Utils.convertStringToDate(strLetterDate);

		String letterNo = (String) mapResult.get("Letter_No__c");
		String level = (String) mapResult.get("Level__c");
		String strStartDate = (String) mapResult.get("Start_Date__c");
		Date startDate = null;
		if (strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);

		String subDepartment = (String) mapResult.get("Sub_Department__c");
		String workLocation = (String) mapResult.get("Work_Location_Name__c");

		Assignment assignment = new Assignment();
		assignment.setEmploymentExtId(employmentExtId);
		;
		assignment.setEmployeeType(type);
		assignment.setEmployeeStatus(status);
		assignment.setExtId(extId);
		assignment.setName(name);
		assignment.setJobTitleExtId(jobTitleExtId);
		assignment.setEmploymentDirectToExtId(strEmploymentSupervisorExtId);
		assignment.setSubDeparment(subDepartment);
		assignment.setDivision(divisi);
		assignment.setDescription(description);
		assignment.setEmployeeStatus(employeeStatus);
		assignment.setEmployeeType(employeeType);
		assignment.setEndDate(endDate);
		assignment.setStartDate(startDate);
		assignment.setExtId(extId);
		assignment.setCategory(category);
		assignment.setStartDate(startDate);
		assignment.setEndDate(endDate);
		assignment.setLetterDate(letterDate);
		assignment.setLetterNo(letterNo);
		assignment.setLevel(level);
		assignment.setWorkLocation(workLocation);

		// assignment.setDeparment(department);
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
			assignment.setEmploymentExtId(e.getEmploymentExtId());
			assignment.setCategory(e.getCategory());
			assignment.setEmployeeStatus(e.getEmployeeStatus());
			assignment.setEmployeeType(e.getEmployeeType());
			assignment.setExtId(e.getExtId());
			assignment.setEmploymentExtId(e.getEmploymentExtId());
			;

			assignment.setExtId(e.getExtId());
			assignment.setName(e.getName());
			assignment.setJobTitleExtId(e.getJobTitleExtId());
			assignment.setEmploymentDirectToExtId(e
					.getEmploymentDirectToExtId());
			assignment.setSubDeparment(e.getSubDeparment());
			assignment.setDivision(e.getDivision());
			assignment.setDescription(e.getDescription());
			assignment.setEndDate(e.getEndDate());
			assignment.setStartDate(e.getStartDate());
			assignment.setExtId(e.getExtId());
			assignment.setCategory(e.getCategory());
			assignment.setStartDate(e.getStartDate());
			assignment.setEndDate(e.getEndDate());
			assignment.setLetterDate(e.getLetterDate());
			assignment.setLetterNo(e.getLetterNo());
			assignment.setLevel(e.getLevel());
			assignment.setWorkLocation(e.getWorkLocation());

			Employment employment = employmentService.findByExtId(e
					.getEmploymentExtId());
			if (employment != null) {
				assignment.setEmployment(employment.getId());
			}

			JobTitle jobTitle = jobTitleService.findByExtId(e
					.getJobTitleExtId());
			if (jobTitle != null) {
				assignment.setJobTitle(jobTitle.getId());
			}

			Employment employmentDirectTo = employmentService.findByExtId(e
					.getEmploymentDirectToExtId());
			if (employmentDirectTo != null) {
				assignment.setEmploymentDirectTo(employmentDirectTo.getId());
			}
			assignment.setCompany(this.companyid);
			assignment.setCreatedDate(new Date());
			assignment.setModifiedDate(new Date());
			assignment.setModifiedBy("Talents Gateway");
			assignment.setCreatedBy("Talents Gateway");
			assignmentService.save(assignment);
			System.out.println("Success Save Assignment");
		}
	}

}
