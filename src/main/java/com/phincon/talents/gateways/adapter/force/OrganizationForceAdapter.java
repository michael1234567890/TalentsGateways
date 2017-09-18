package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Organization;
import com.phincon.talents.gateways.repository.OrganizationRepository;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.OrganizationService;

@Service
public class OrganizationForceAdapter extends ForceAdapter<Organization> {

	@Autowired
	OrganizationService organizationService;
	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	
	public OrganizationForceAdapter() {
		super();
	}
	
	@Override
	protected Organization convertMapResultToObject(Map<String, Object> mapResult){
		
		
		String name = (String) mapResult.get("Name");
		String endDate = (String) mapResult.get("End_Date__c");
		String startDate = (String) mapResult.get("Start_Date__c");
		Integer organizationLevel = (Integer) mapResult.get("Organization_Level__c");
		String organizationStructureName = (String) mapResult.get("Organization_Structure_Name__c");
		Integer organizationSubLevel = (Integer) mapResult.get("Organization_Sub_Level__c");
		String organizationTracking = (String) mapResult.get("Organization_Tracking__c");
		String organizationType = (String) mapResult.get("Organization_Type__c");
		String remark = (String) mapResult.get("Remark__c");
		String extId = (String) mapResult.get("Id");
		
		
		
		
		Organization obj = new Organization();
		obj.setExtId(extId);
		obj.setName(name);
		obj.setEndDate(endDate);
		obj.setOrganizationLevel(organizationLevel);
		obj.setOrganizationSubLevel(organizationSubLevel);
		obj.setOrganizationTracking(organizationTracking);
		obj.setOragnizationType(organizationType);
		obj.setRemark(remark);
		obj.setStartDate(startDate);
		obj.setOrganizationStructureName(organizationStructureName);
		
		
		return obj;
	}
	
	@Override
	public void saveListData(List<Organization> listData, boolean isInit){
		for(Organization e : listData){
			Organization organization = organizationService.findByExtId(e.getExtId());
			
			if(organization == null){
				organization = new Organization() ;
				organization.setCreatedDate(new Date());
				organization.setCreatedBy("Talents Gateway");
				
			}
			
			if(isInit)
				organization.setAckSync(false);
			
			organization.setExtId(e.getExtId());
			organization.setCompany(this.companyid);
			organization.setName(e.getName());
			organization.setEndDate(e.getEndDate());
			organization.setOrganizationLevel(e.getOrganizationLevel());
			organization.setOrganizationSubLevel(e.getOrganizationSubLevel());
			organization.setOrganizationTracking(e.getOrganizationTracking());
			organization.setOragnizationType(e.getOragnizationType());
			organization.setRemark(e.getRemark());
			organization.setStartDate(e.getStartDate());
			organization.setOrganizationStructureName(e.getOrganizationStructureName());
			organizationService.save(organization);
			System.out.println("Organization Has been saved");
		}
	}
	
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = organizationRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		organizationService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		organizationService.updateAckSyncStatus(status, extId);
	}
	
}
