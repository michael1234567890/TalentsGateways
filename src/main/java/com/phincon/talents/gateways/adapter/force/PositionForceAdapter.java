package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Organization;
import com.phincon.talents.gateways.model.Position;
import com.phincon.talents.gateways.repository.PositionRepository;
import com.phincon.talents.gateways.services.OrganizationService;
import com.phincon.talents.gateways.services.PositionService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class PositionForceAdapter extends ForceAdapter<Position>{
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	OrganizationService organizationService;
	
	public PositionForceAdapter(){
		super();
		query = "WSSETPOSITION";
	}
	
	@Override
	protected Position convertMapResultToObject(Map<String, Object> mapResult){
		String extId = (String) mapResult.get("Id");
		String strStartDate = (String) mapResult.get("Start_Date__c");
		Date startDate = null;
		if(strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);
		String strEndDate = (String) mapResult.get("End_Date__c");
		Date endDate = null;
		if(strEndDate != null)
			endDate = Utils.convertStringToDate(strEndDate);
		String fromGrade = (String) mapResult.get("From_Grade__c");
		String toGrade = (String) mapResult.get("To_Grade__c");
		String name = (String) mapResult.get("Name");
		String directPositionName = (String) mapResult.get("Direct_Position_Name__c");
		Boolean flagOperational = (Boolean) mapResult.get("Flag_Operational__c");
		String strFlagOperational = null;
		if(flagOperational.booleanValue() == true)
			strFlagOperational = "true";
		else
			strFlagOperational = "false";
		String jobTitleName = (String) mapResult.get("Job_Title_Name__c");
		String letterNo = (String) mapResult.get("Letter_No__c");
		Integer maxMarketSalary = (Integer) mapResult.get("Max_Market_Salary__c");
		Integer minMarketSalary = (Integer) mapResult.get("Min_Market_Salary__c");
		String organizationName = (String) mapResult.get("Organization_Name__c");
		String jobDescription = (String) mapResult.get("Position_Job_Description__c");
		String level = (String) mapResult.get("Position_Level__c");
		String levelDetail = (String) mapResult.get("Position_Level_Detail__c");
		String type = (String) mapResult.get("Position_Type__c");
		Boolean used = (Boolean) mapResult.get("Position_Used__c");
		String strUsed = null;
		if(used.booleanValue() == true)
			strUsed = "true";
		else
			strUsed = "false";
		String remark = (String) mapResult.get("Remark__c");
		Integer totalBudget = (Integer) mapResult.get("Position_Total_Budget__c");
		Integer totalNumber = (Integer) mapResult.get("Total_Number__c");
		
				
		
		Position position = new Position();
		position.setOrganizationExtId(organizationName);
		position.setExtId(extId);
		position.setStartDate(startDate);
		position.setEndDate(endDate);
		position.setFromGrade(fromGrade);
		position.setToGrade(toGrade);
		position.setName(name);
		position.setDirectPositionName(directPositionName);
		position.setFlagOperational(strFlagOperational);
		position.setJobTitleName(jobTitleName);
		position.setLetterNo(letterNo);
		position.setMaxMarketSalary(maxMarketSalary);
		position.setMinMarketSalary(minMarketSalary);
		position.setOrganizationName(organizationName);
		position.setJobDescription(jobDescription);
		position.setLevel(level);
		position.setLevelDetail(levelDetail);
		position.setType(type);
		position.setUsed(strUsed);
		position.setRemark(remark);
		position.setTotalBudget(totalBudget);
		position.setTotalNumber(totalNumber);
		
		return position;
	}
	
	@Override
	public void saveListData(List<Position> listData, boolean isInit){
		for(Position e : listData){
			Position position = positionService.findByExtId(e.getExtId());
			
			if(position == null){
				position = new Position();
				position.setCreatedDate(new Date());
			}
			
			Organization organization = organizationService.findByExtId(e.getOrganizationExtId());
			if(organization != null)
				position.setOrganization(organization.getId());
			
			
			if(isInit)
				position.setAckSync(false);
			
			position.setExtId(e.getExtId());
			position.setCompany(this.companyid);
			position.setStartDate(e.getStartDate());
			position.setEndDate(e.getEndDate());
			position.setFromGrade(e.getFromGrade());
			position.setToGrade(e.getToGrade());
			position.setName(e.getName());
			position.setDirectPositionName(e.getDirectPositionName());
			position.setFlagOperational(e.getFlagOperational());
			position.setJobTitleName(e.getJobTitleName());
			position.setLetterNo(e.getLetterNo());
			position.setMaxMarketSalary(e.getMaxMarketSalary());
			position.setMinMarketSalary(e.getMinMarketSalary());
			position.setOrganizationName(e.getOrganizationName());
			position.setJobDescription(e.getJobDescription());
			position.setLevel(e.getLevel());
			position.setLevelDetail(e.getLevelDetail());
			position.setType(e.getType());
			position.setUsed(e.getUsed());
			position.setRemark(e.getRemark());
			position.setTotalBudget(e.getTotalBudget());
			position.setTotalNumber(e.getTotalNumber());
			position.setModifiedDate(new Date());
			position.setModifiedBy("Talents Gateway");
			position.setCreatedBy("Talents Gateway");
			positionService.save(position);
			System.out.println("Success Save Position");
		}
	}
	
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = positionRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		positionService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		positionService.updateAckSyncStatus(status, extId);
	}
	
}
