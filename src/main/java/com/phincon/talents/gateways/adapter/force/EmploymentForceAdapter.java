package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.repository.EmploymentRepository;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class EmploymentForceAdapter extends ForceAdapter<Employment> {

	@Autowired
	EmploymentService EmploymentService;
	
	@Autowired
	EmploymentRepository employmentRepository;
	

	@Autowired
	EmployeeService employeeService;
	
	
	public EmploymentForceAdapter() {
		super();
		
		query = "hrempinfo";
	}
	
	@Override
	protected Employment convertMapResultToObject(Map<String, Object> mapResult){
		String name = (String) mapResult.get("Name");
		String employeeExtId = (String) mapResult.get("Full_Name__c");
		String extId = (String) mapResult.get("Id");
		String account = (String) mapResult.get("Account_Name__c");
		
		Date eopDate = null;
		String strEopDate = (String) mapResult.get("EOP_Date__c");
		if(strEopDate != null)
			eopDate = Utils.convertStringToDate(strEopDate);
		
		Date endDateOfContract = null; 
		String strEndDateOfContract = (String) mapResult.get("End_Date_of_Contract__c");
		if(strEndDateOfContract != null)
			endDateOfContract = Utils.convertStringToDate(strEndDateOfContract);
		
		String employeeType = (String) mapResult.get("Employee_Type__c");
		String strEffectiveDate = (String) mapResult.get("Effective_Date__c");
		Date effectiveDate = null; 
		if(strEffectiveDate != null)
			effectiveDate = Utils.convertStringToDate(strEffectiveDate);
		
		String description = (String) mapResult.get("Description__c");
		String resignation = (String) mapResult.get("Resignation__c");
		String reason = (String) mapResult.get("Reason__c");
		
		String strTerminationDate = (String) mapResult.get("Terminate_Date__c");
		Date terminationDate = null;
		if(strTerminationDate != null)
		 terminationDate = Utils.convertStringToDate(strTerminationDate);
		
		String terminationNotes = (String) mapResult.get("Termination_Notes__c");
		String letterNo = (String) mapResult.get("Letter_No__c");
		
		String strLetterDate = (String) mapResult.get("Letter_Date__c");
		Date letterDate = null;
		if(strLetterDate != null)
		 letterDate = Utils.convertStringToDate(strLetterDate);
		
		Employment employment = new Employment();
		employment.setAccountName(account);
		employment.setName(name);
		employment.setEmployeeType(employeeType);
		employment.setDescription(description);
		employment.setEffectiveDate(effectiveDate);
		
		employment.setEmployeeExtId(employeeExtId);
		employment.setEndDateContract(endDateOfContract);
		employment.setEopDate(eopDate);
		employment.setExtId(extId);
		employment.setLetterDate(letterDate);
		employment.setLetterNo(letterNo);
		employment.setModifiedDate(new Date());
		employment.setTerminationDate(terminationDate);
		employment.setTerminationNotes(terminationNotes);
		employment.setReason(reason);
		employment.setResignation(resignation);
		
		
		return employment;
	}
	
	@Override
	public void saveListData(List<Employment> listData, boolean isInit){
		for(Employment e : listData){
			Employment employment = EmploymentService.findByExtId(e.getExtId());
			
			if(employment == null){
				employment = new Employment();
				employment.setCreatedDate(new Date());
			}
			employment.setExtId(e.getExtId());
			if(isInit)
				employment.setAckSync(false);
			
			employment.setCompany(this.companyid);
			employment.setDescription(e.getDescription());
			employment.setEffectiveDate(e.getEffectiveDate());
			employment.setEndDateContract(e.getEndDateContract());
			employment.setEopDate(e.getEopDate());
			employment.setLetterDate(e.getLetterDate());
			employment.setLetterNo(e.getLetterNo());
			employment.setReason(e.getReason());
			employment.setResignation(e.getResignation());
			employment.setTerminationDate(e.getTerminationDate());
			employment.setTerminationNotes(e.getTerminationNotes());
			employment.setName(e.getName());
			employment.setEmployeeType(e.getEmployeeType());
			Employee employee = employeeService.findByExtId(e.getEmployeeExtId());
			if (employee != null) {
				employment.setEmployee(employee);
			}
			employment.setModifiedBy("Talents Gateway");
			employment.setCreatedBy("Talents Gateway");
			employment.setEmployeeExtId(e.getEmployeeExtId());			
			employment.setModifiedDate(new Date());
			EmploymentService.save(employment);
			System.out.println("Success Save Employment");
		}
	}
	
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = employmentRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		employmentRepository.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		employmentRepository.updateAckSyncStatus(status, extId);
	}
}
