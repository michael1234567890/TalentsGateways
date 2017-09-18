package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.TMBalance;
import com.phincon.talents.gateways.repository.TMBalanceRepository;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.TMBalanceService;
import com.phincon.talents.gateways.utils.ForceResponseGetId;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class TMBalanceForceAdapter extends ForceAdapter<TMBalance> {

	@Autowired
	TMBalanceService tmBalanceService;
	
	@Autowired
	TMBalanceRepository tmBalanceRepository;
	

	@Autowired
	EmploymentService employmentService;
	
	
	
	public TMBalanceForceAdapter() {
		super();
	}
	
	@Override
	protected TMBalance convertMapResultToObject(Map<String, Object> mapResult){
		String extId = (String) mapResult.get("Id");
		Double balanceLimit = (Double) mapResult.get("Balance_Limit__c");
		Double balanceUsed = (Double) mapResult.get("Balance_Used__c");
		Double balanceValid = (Double) mapResult.get("Balance_Valid__c");
		Double balance = (Double) mapResult.get("Balance__c");
		String employeeExtId = (String) mapResult.get("Employee_No__c");
		String strEndDate = (String) mapResult.get("End_Date__c");
		Date endDate = null;
		if(strEndDate != null)
			endDate = Utils.convertStringToDate(strEndDate);
		String module = (String) mapResult.get("Module__c");
		String name = (String) mapResult.get("Name");
		String period = (String) mapResult.get("Period__c");
		String balanceType = (String) mapResult.get("Balance_Type__c");
		String strLastClaimDate = (String) mapResult.get("Last_Claim_Date__c");
		Date lastClaimDate = null;
		if(strLastClaimDate != null)
			lastClaimDate = Utils.convertStringToDate(strLastClaimDate);
		
		String categoryTypeId = (String) mapResult.get("RecordTypeId");
		String categoryType = null;
		if(categoryTypeId != null) {
			Map<String, Object> recordType = (Map<String, Object>) mapResult.get("RecordType");
			if(recordType != null)
				categoryType = (String) recordType.get("Name");
		}
		String strStartDate = (String) mapResult.get("Start_Date__c");
		Date startDate = null;
		if(strStartDate != null)
			startDate= Utils.convertStringToDate(strStartDate);
		
		String type = (String) mapResult.get("Transaction_Type__c");
	      
		
		
		TMBalance obj = new TMBalance();
		obj.setBalanceType(balanceType);
		obj.setLastClaimDate(lastClaimDate);
		obj.setExtId(extId);
		obj.setBalanceEnd(balance);
		obj.setBalanceLimit(balanceLimit);
		obj.setBalanceUsed(balanceUsed);
		obj.setBalanceValid(balanceValid);
		obj.setCategoryType(categoryType);
		obj.setCategoryTypeId(categoryTypeId);
		obj.setEmploymentExtId(employeeExtId);
		obj.setEndDate(endDate);
		obj.setStartDate(startDate);
		obj.setModule(module);
		obj.setName(name);
		obj.setPeriod(period);
		obj.setRemark("");
		obj.setType(type);
			
		return obj;
	}
	
	@Override
	public void saveListData(List<TMBalance> listData, boolean isNull){
		for(TMBalance e : listData){
			TMBalance obj = tmBalanceService.findByExtId(e.getExtId());
			
			if(obj == null){
				obj = new TMBalance() ;
				obj.setCreatedDate(new Date());
				obj.setCreatedBy("Talents Gateway");
				obj.setExtId(e.getExtId());
			}
			
			if(isNull)
				obj.setAckSync(false);
			
			obj.setBalanceEnd(e.getBalanceEnd());
			obj.setBalanceLimit(e.getBalanceLimit());
			obj.setBalanceType(e.getBalanceType());
			obj.setBalanceUsed(e.getBalanceUsed());
			obj.setBalanceValid(e.getBalanceValid());
			obj.setCategoryType(e.getCategoryType());
			obj.setCategoryTypeId(e.getCategoryTypeId());
			obj.setEmploymentExtId(e.getEmploymentExtId());
			obj.setEndDate(e.getEndDate());
			obj.setStartDate(e.getStartDate());
			obj.setModule(e.getModule());
			obj.setName(e.getName());
			obj.setPeriod(e.getPeriod());
			obj.setRemark("");
			obj.setType(e.getType());
			
			Employment employment = null;
			if(e.getEmploymentExtId() != null){
				employment = employmentService.findByExtId(e.getEmploymentExtId());
				if(employment != null)
					obj.setEmployment(employment.getId());
			}
			obj.setCompany(this.companyid);
			obj.setModifiedDate(new Date());
			obj.setModifiedBy("Talents Gateway");
			tmBalanceService.save(obj);
			
			System.out.println("TMBalance Save");
		}
	}
	
	@Override
	public void sendNewData() {
		// get data with ext id is null
		System.out.println("Send New Data");
		List<TMBalance> listBalance = tmBalanceService.findNeedSync();
		listBalance(listBalance);
	}
	
	public void listBalance(List<TMBalance> listBalance){
		if (listBalance != null) {
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (TMBalance request : listBalance) {
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("id", family.getId());
				if(request.getExtId() != null)
					map.put("Id", request.getExtId());
				
				map.put("ExtId__c", request.getUuid());
				map.put("Balance_Used__c", request.getBalanceUsed());
				map.put("Last_Claim_Date__c", request.getLastClaimDate());
				map.put("Adjustment__c", request.getAdjustmentMedical());
				
				
				listMap.add(map);
				
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + " Task Already Sending ");
		}
	}
	
	@Override
	public void updateExtId( List<Map<String, Object>> list) {
		//System.out.println("ID " + id + " , ExtId " + extId);
		List<Map<String, Object>> listMapUpdateId = new ArrayList();
		for (Map<String, Object> map : list) {
			Map<String, Object> mapUpdateId = new HashMap<String, Object>();
			UUID uuid = (UUID) map.get("ExtId__c");
			mapUpdateId.put("ExtId__c", uuid);
			listMapUpdateId.add(mapUpdateId);
			
		}
		
		// prepare sending update Ext ID
		Map<String, Object> mapPost = new HashMap<String, Object>();
		mapPost.put("items", listMapUpdateId);
		MultiValueMap<String, String> headersPost = new LinkedMultiValueMap<String, String>();
		headersPost.add("Authorization", "Bearer " + accessToken);
		headersPost.add("Content-Type", "application/json");
		String urlQuery = this.instanceUrl + "/services/apexrest/GetIdbyExtId?SyncObject="+this.forceModuleName;
		try {
			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(
					mapPost, headersPost);
			String response = restTemplate.postForObject(urlQuery, request,
					String.class);

			System.out.println("Reponse Post " + response);
			
			ForceResponseGetId forceResponseGetId = (ForceResponseGetId) objectMapper
					.readValue(response, ForceResponseGetId.class);
			List<Map<String,Object>> listResponseGetId = forceResponseGetId.getResults();
			
			for (Map<String, Object> map : listResponseGetId) {
				String extId = (String) map.get("Id");
				String uuid = (String) map.get("ExtId__c");
				tmBalanceService.updateExtIdByUUID(extId, uuid);
			}
			
		} catch (HttpClientErrorException ex) {
			System.out.println("Error HTTP Client " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());

		}
		
	}
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = tmBalanceRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		tmBalanceService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		tmBalanceService.updateAckSyncStatus(status, extId);
	}
	
}
