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

import com.phincon.talents.gateways.model.Certification;
import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.repository.CertificationRepository;
import com.phincon.talents.gateways.services.CertificationService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.utils.ForceResponseGetId;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class CertificationForceAdapter extends ForceAdapter<Certification>{

	@Autowired
	CertificationService certificationService;
	
	@Autowired
	CertificationRepository certificationRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	public CertificationForceAdapter() {
		super();
		/*query = "select Id,Name,Certification_Date__c,Certification_Description__c,Certification_Expired__c,"
				+ "Certification_Full__c,Certification_No__c,Certification_Type__c,Full_Name__c,Principle__c,"
				+ "Year_Certified__c"
				+ " from HRPERCERTIFICATION__c limit 10";*/
		query = "GetAllHRPERCERTIFICATION";
	}
	
	@Override
	protected Certification convertMapResultToObject(Map<String, Object> mapResult){		
		String extId = "";
		if(mapResult.get("Id") != null){
			extId = (String) mapResult.get("Id");
		}else{
			extId = null;
		}
		String name = "";
		if(mapResult.get("Name") != null){
			name = (String) mapResult.get("Name");
		}else{
			name = null;
		}
		String strCertificationDate = null;
		if(mapResult.get("Certification_Date__c") != null){
			strCertificationDate = (String) mapResult.get("Certification_Date__c");
		}else{
			strCertificationDate = null;
		}
		String certificationDescription = "";
		if(mapResult.get("Certification_Description__c") != null){
			certificationDescription = (String) mapResult.get("Certification_Description__c");
		}else{
			certificationDescription = null;
		}
		String strCertificationExpired = "";
		if(mapResult.get("Certification_Expired__c") != null){
			strCertificationExpired = (String) mapResult.get("Certification_Expired__c");
		}else{
			strCertificationExpired = null;
		}
		String certificationFull = "";
		if(mapResult.get("Certification_Full__c") != null){
			certificationFull = (String) mapResult.get("Certification_Full__c");
		}else{
			certificationFull = null;
		}
		String certificationNo = "";
		if(mapResult.get("Certification_No__c") != null){
			certificationNo = (String) mapResult.get("Certification_No__c");
		}else{
			certificationNo = null;
		}
		String certificationType = "";
		if(mapResult.get("Certification_Type__c") != null){
			certificationType = (String) mapResult.get("Certification_Type__c");
		}else{
			certificationType = null;
		}
		//String fullName = (String) mapResult.get("Full_Name__c");
		String empExtId = null;
		if(mapResult.get("Full_Name__c") != null)
			empExtId = (String) mapResult.get("Full_Name__c");
		
		String principle = "";
		if(mapResult.get("Principle__c") != null){
			principle = (String) mapResult.get("Principle__c");
		}else{
			principle = null;
		}
		String yearCertified = "";
		if(mapResult.get("Year_Certified__c") != null){
			yearCertified = (String) mapResult.get("Year_Certified__c");
		}else{
			yearCertified = null;
		}
		Date certificationDate = null;
		Date certificationExpired = null;
		if(strCertificationDate != null)
			certificationDate = Utils.convertStringToDate(strCertificationDate);
		if(strCertificationExpired != null)
			certificationExpired = Utils.convertStringToDate(strCertificationExpired);

		Certification certification = new Certification();
		certification.setExtId(extId);
		certification.setName(name);
		certification.setDate(certificationDate);
		certification.setDescription(certificationDescription);
		certification.setExpired(certificationExpired);
		certification.setFull(certificationFull);
		certification.setNo(certificationNo);
		certification.setType(certificationType);
		certification.setPrinciple(principle);
		certification.setYear(yearCertified);
		certification.setEmployeeExtId(empExtId);

		return certification;
	}
	
	@Override
	public void saveListData(List<Certification> listData,boolean isInit){
		for(Certification e : listData){
			System.out.println("Certification : " + e.getExtId());
			// check is id is exist
			Certification certDb = certificationService.findByExtId(e.getExtId());
			// if exist doing update
			if(certDb == null){
				certDb = new Certification();
				certDb.setCreatedDate(new Date());
			}
			certDb.setExtId(e.getExtId());
			if(isInit)
				certDb.setAckSync(false);
			
			certDb.setName(e.getName());
			certDb.setDate(e.getDate());
			certDb.setDescription(e.getDescription());
			certDb.setExpired(e.getExpired());
			certDb.setFull(e.getFull());
			certDb.setNo(e.getNo());
			certDb.setType(e.getType());
			certDb.setPrinciple(e.getPrinciple());
			certDb.setYear(e.getYear());
			if(e.getEmployeeExtId() != null) {
				Employee employee = employeeService.findByExtId(e.getEmployeeExtId());
				System.out.println(employee.toString());
				if (employee != null) {
					certDb.setEmployee(employee);
				}
			}
			
			certDb.setEmployeeExtId(e.getEmployeeExtId());
		
			certDb.setModifiedDate(new Date());
			certificationService.save(certDb);
			System.out.println("Success Save Certification");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		List<Certification> listCertification = certificationService.findNeedSync();
		if(listCertification != null){
			System.out.println("Jumlah List " + listCertification.size());
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Certification certification : listCertification){
				System.out.println("Looping "+ i);
				Map<String, Object> map = new HashMap<String, Object>();
				if(certification.getExtId() != null)
					map.put("Id", certification.getExtId());
				map.put("Full_Name__c", certification.getEmployeeExtId());
				map.put("ExtId__c", certification.getUuid());
				map.put("Name", certification.getName());
				map.put("Certification_Date__c", certification.getDate());
				map.put("Certification_Description__c", certification.getDescription());
				map.put("Certification_Expired__c", certification.getExpired());
				map.put("Certification_Full__c", certification.getFull());
				map.put("Certification_No__c", certification.getNo());
				map.put("Certification_Type__c", certification.getType());
				map.put("Principle__c", certification.getPrinciple());
				map.put("Year_Certified__c", certification.getYear());
				listMap.add(map);
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + " Task Already Sending");
		}
	}
	
	public void updateExtId(List<Map<String, Object>> list){
		List<Map<String, Object>> listMapUpdateId = new ArrayList<>();
		for(Map<String, Object> map : list){
			Map<String, Object> mapUpdateId = new HashMap<String, Object>();
			UUID uuid = (UUID) map.get("ExtId__c");
			mapUpdateId.put("ExtId__c", uuid);
			listMapUpdateId.add(mapUpdateId);
		}
		
		// prepare sending update Ext ID
		Map<String, Object> mapPost = new HashMap<String, Object>();
		mapPost.put("items", listMapUpdateId);
		MultiValueMap<String, String> headerPost = new LinkedMultiValueMap<String, String>();
		headerPost.add("Authorization", "Bearer " + accessToken );
		headerPost.add("Content-Type", "application/json");
		String urlQuery = this.instanceUrl + "/services/apexrest/GetIdbyExtId?SyncObject=HRPERCERTIFICATION__c";
		System.out.println("URL Query : " + urlQuery);
		
		try{
			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(
					mapPost, headerPost);
			String response = restTemplate.postForObject(urlQuery, request,
					String.class);
			
			System.out.println("Response Post Ext Id " + response);
			
			ForceResponseGetId forceResponseGetId = (ForceResponseGetId) objectMapper
					.readValue(response, ForceResponseGetId.class);
			List<Map<String,Object>> listResponseGetId = forceResponseGetId.getResults();
			
			for(Map<String, Object> map : listResponseGetId){
				String extId = (String) map.get("Id");
				String uuid = (String) map.get("ExtId__c");
				certificationService.updateExtIdByUUID(extId, uuid);
			}
		}catch(HttpClientErrorException ex){
			System.out.println("Error HTTP Client : " + ex.getMessage());
		}catch(Exception ex){
			System.out.println("Error : " + ex.getMessage());
		}
		
	}
	
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = certificationRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		certificationService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		certificationService.updateAckSyncStatus(status, extId);
	}
	
	


}
