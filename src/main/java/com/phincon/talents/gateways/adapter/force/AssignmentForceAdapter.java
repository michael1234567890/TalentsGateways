package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Assignment;
import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.Grade;
import com.phincon.talents.gateways.model.JobTitle;
import com.phincon.talents.gateways.model.Organization;
import com.phincon.talents.gateways.model.Position;
import com.phincon.talents.gateways.repository.AssignmentRepository;
import com.phincon.talents.gateways.repository.GradeRepository;
import com.phincon.talents.gateways.repository.OrganizationRepository;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.JobTitleService;
import com.phincon.talents.gateways.services.PositionService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class AssignmentForceAdapter extends ForceAdapter<Assignment> {

	@Autowired
	AssignmentService assignmentService;
	
	
	@Autowired
	AssignmentRepository assignmentRepository;
	

	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	PositionService positionService;

	@Autowired
	JobTitleService jobTitleService;

	public AssignmentForceAdapter() {
		super();
		query = "HREMPASSIGNMENT";
	}
	

	/*
	 * convert result from Force to Object Assignment
	 * input : map String Object
	 * output : Object Assignment
	 */
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
		String positionExtId = (String) mapResult.get("Position_Name__c");
		String gradeExtId = (String) mapResult.get("Grade_Name__c");
		String organizationExtId = (String) mapResult.get("Organization_Name__c");
		
		
		Assignment assignment = new Assignment();
		assignment.setOrganizationExtId(organizationExtId);
		assignment.setPositionExtId(positionExtId);
		assignment.setEmploymentExtId(employmentExtId);;
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
		assignment.setGradeExtId(gradeExtId);

		// assignment.setDeparment(department);
		return assignment;
	}
	

	/*
	 * save list object into Table
	 */
	@Override
	public void saveListData(List<Assignment> listData,boolean isInit) {
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
			assignment.setPositionExtId(e.getPositionExtId());
			
			// flag ack sync as false if doing init action
			if(isInit)
				assignment.setAckSync(false);
			
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
			assignment.setGradeExtId(e.getGradeExtId());
			assignment.setOrganizationExtId(e.getOrganizationExtId());
			
			if(e.getPositionExtId() != null){
				Position position = positionService.findByExtId(e.getPositionExtId());
				if (position != null) {
					assignment.setPosition(position.getId());
				}
				
			}
			
			if(e.getOrganizationExtId() != null){
				Organization organization = organizationRepository.findByExtId(e.getOrganizationExtId());
				if (organization != null) {
					assignment.setOrganization(organization.getId());
				}
				
			}
			
			
			if(e.getEmploymentExtId() != null) {
				Employment employment = employmentService.findByExtId(e
						.getEmploymentExtId());
				if (employment != null) {
					assignment.setEmployment(employment.getId());
				}
			}
			

			if(e.getJobTitleExtId() != null) {
				JobTitle jobTitle = jobTitleService.findByExtId(e
						.getJobTitleExtId());
				if (jobTitle != null) {
					assignment.setJobTitle(jobTitle.getId());
				}
			}
			
			if(e.getGradeExtId() != null) {
				Grade grade = gradeRepository.findByExtId(e.getGradeExtId());
				if (grade != null) {
					assignment.setGrade(grade.getId());
				}
			}
			

			if(e.getEmploymentDirectToExtId() != null) {
				Employment employmentDirectTo = employmentService.findByExtId(e
						.getEmploymentDirectToExtId());
				if (employmentDirectTo != null) {
					assignment.setEmploymentDirectTo(employmentDirectTo.getId());
				}
			}
			
			assignment.setCompany(this.companyid);
			assignment.setCreatedDate(new Date());
			assignment.setModifiedDate(new Date());
			assignment.setModifiedBy("Talents Gateway");
			assignment.setCreatedBy("Talents Gateway");
			assignmentService.save(assignment);
		}
	}
	

	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = assignmentRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		assignmentService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		assignmentService.updateAckSyncStatus(status, extId);
	}

}
