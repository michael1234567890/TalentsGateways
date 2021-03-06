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

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.model.HistorySync;
import com.phincon.talents.gateways.repository.FamilyRepository;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.FamilyService;
import com.phincon.talents.gateways.services.HistorySyncService;
import com.phincon.talents.gateways.utils.ForceResponseGetId;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class FamilyForceAdapter extends ForceAdapter<Family> {

	@Autowired
	FamilyService familyService;
	
	@Autowired
	FamilyRepository familyRepository;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	HistorySyncService historySyncService;

	public FamilyForceAdapter() {
		super();
		query = "GetAllHRPERFAMILY";
	}

	@Override
	protected Family convertMapResultToObject(Map<String, Object> mapResult) {
		String name = (String) mapResult.get("Name");
		String employeeExtId = (String) mapResult.get("Name__c");
		String extId = (String) mapResult.get("Id");
		String address = (String) mapResult.get("Family_Address__c");
		String birthPlace = (String) mapResult.get("Family_Birth_Place__c");
		String bloodType = (String) mapResult.get("Family_Blood_Type__c");
		String strBirthDate = (String) mapResult.get("Family_Date_of_Birth__c");
		String strDeceaseDate = (String) mapResult
				.get("Family_Decease_Date__c");
		String dependent = (String) mapResult.get("Family_Dependent__c");
		String lastEducation = (String) mapResult
				.get("Family_Last_Education__c");
		String medicalStatus = (String) mapResult
				.get("Family_Medical_Status__c");
		String aliveStatus = (String) mapResult.get("Alive_Status__c");
		String occupation = (String) mapResult.get("Family_Occupation__c");
		String phone = (String) mapResult.get("Family_Phone__c");
		String relationship = (String) mapResult.get("Family_Relationship__c");
		String gender = (String) mapResult.get("Gender__c");
		String letterNo = (String) mapResult.get("Letter_No__c");
		String maritalStatus = (String) mapResult.get("Marital_Status__c");
		
		String district =(String) mapResult.get("District__c"); 
		String subdistrict = (String) mapResult.get("Subdistrict__c");
		String nircNo = (String) mapResult.get("NRIC_No__c");
		String familyCardNo = (String) mapResult.get("Letter_No__c");
		String rw = (String) mapResult.get("RW__c");
		String rt = (String) mapResult.get("RT__c");
		String nationality = (String) mapResult.get("Nationality__c");
		String polisNo = (String) mapResult.get("Nomor_Polis_Asuransi__c");
		String assuranceName = (String) mapResult.get("Nama_Asuransi__c");
		String npwpNo = (String) mapResult.get("No_NPWP__c");
		String passportNo = (String) mapResult.get("No_Passport__c");
		String zipCode = (String) mapResult.get("ZIP_Code__c");
		
		Date birthDate = null;
		Date deceaseDate = null;
		if (strDeceaseDate != null)
			deceaseDate = Utils.convertStringToDate(strDeceaseDate);
		if (strBirthDate != null)
			birthDate = Utils.convertStringToDate(strBirthDate);

		Family family = new Family();
		family.setZipCode(zipCode);
		family.setPassportNo(passportNo);
		family.setNpwpNo(npwpNo);
		family.setAssuranceName(assuranceName);
		family.setPolisNo(polisNo);
		family.setNationality(nationality);
		family.setRt(rt);
		family.setRw(rw);
		family.setFamilyCardNo(familyCardNo);
		family.setNircNo(nircNo);
		family.setSubDistrict(subdistrict);
		family.setDistrict(district);
		
		family.setAliveStatus(aliveStatus);
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
	public void saveListData(List<Family> listData, boolean isInit) {
		for (Family e : listData) {
			System.out.print("Employee : " + e.getExtId());
			// check is id is exist
			Family family = familyService.findByExtId(e.getExtId());

			// if exist doing update
			if (family == null) {
				family = new Family();
				family.setCreatedDate(new Date());
			}
			
			if(isInit)
				family.setAckSync(false);

			family.setAddress(e.getAddress());
			family.setBirthDate(e.getBirthDate());
			family.setBirthPlace(e.getBirthPlace());
			family.setBloodType(e.getBloodType());
			family.setDeceaseDate(e.getDeceaseDate());
			family.setDependent(e.getDependent());
			family.setExtId(e.getExtId());
			family.setGender(e.getGender());
			family.setAliveStatus(e.getAliveStatus());
			family.setLastEducation(e.getLastEducation());
			family.setRelationship(e.getRelationship());
			family.setPhone(e.getPhone());
			family.setOccupation(e.getOccupation());
			family.setName(e.getName());
			family.setMedicalStatus(e.getMedicalStatus());
			family.setMaritalStatus(e.getMaritalStatus());
			family.setLetterNo(e.getLetterNo());
			family.setEmployeeExtId(e.getEmployeeExtId());
			family.setZipCode(e.getZipCode());
			family.setPassportNo(e.getPassportNo());
			family.setNpwpNo(e.getNpwpNo());
			family.setAssuranceName(e.getAssuranceName());
			family.setPolisNo(e.getPolisNo());
			family.setNationality(e.getNationality());
			family.setRt(e.getRt());
			family.setRw(e.getRw());
			family.setFamilyCardNo(e.getFamilyCardNo());
			family.setNircNo(e.getNircNo());
			family.setSubDistrict(e.getSubDistrict());
			family.setDistrict(e.getDistrict());
			
			
			Employee employee = employeeService.findByExtId(e
					.getEmployeeExtId());
			if (employee != null) {
				family.setEmployee(employee.getId());
				
			}
			family.setCompany(this.companyid);
			family.setModifiedDate(new Date());
			family.setModifiedBy("Talents Gateway");
			family.setCreatedBy("Talents Gateway");
			familyService.save(family);
			System.out.println("Success Save Family");
		}
	}
	
	public void listFamily(Iterable<Family> listFamily){
		if (listFamily != null) {
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (Family family : listFamily) {
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("id", family.getId());
				if(family.getExtId() != null)
					map.put("Id", family.getExtId());
				
				map.put("ExtId__c", family.getUuid());
				map.put("Name", family.getName());
				map.put("Family_Address__c", family.getAddress());
				map.put("Family_Birth_Place__c", family.getBirthPlace());
				map.put("Family_Blood_Type__c", family.getBloodType());
				map.put("Family_Date_of_Birth__c", family.getBirthDate());
				map.put("Alive_Status__c",family.getAliveStatus());
				// map.put("Family_Decease_Date__c", family.getDeceaseDate());
				map.put("Family_Last_Education__c", family.getLastEducation());
				// map.put("Family_Medical_Status__c",
				// family.getMedicalStatus());
				map.put("Family_Occupation__c", family.getOccupation());
				map.put("Family_Phone__c", family.getPhone());
				map.put("Family_Relationship__c", family.getRelationship());
				map.put("Gender__c", family.getGender());
				map.put("Marital_Status__c", family.getMaritalStatus());
				//map.put("Letter_No__c", family.getLetterNo());
				map.put("Name__c", family.getEmployeeExtId());
				
				map.put("District__c", family.getDistrict());
				map.put("Subdistrict__c", family.getSubDistrict());
				map.put("NRIC_No__c", family.getNircNo());
				map.put("Letter_No__c", family.getFamilyCardNo());
				map.put("RW__c", family.getRw());
				map.put("RT__c", family.getRt());
				
				map.put("Nationality__c", family.getNationality());
				map.put("Nomor_Polis_Asuransi__c", family.getPolisNo());
				map.put("Nama_Asuransi__c", family.getAssuranceName());
				map.put("No_NPWP__c", family.getNpwpNo());
				map.put("No_Passport__c", family.getPassportNo());
				map.put("ZIP_Code__c", family.getZipCode());
				
				listMap.add(map);
				System.out.println("No " + (i + 1) + " : " + family.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + " Task Already Sending ");
		}
	}

	@Override
	public void sendNewData() {
		// get data with ext id is null
		Iterable<Family> listFamily = familyService.findNeedSync();
		listFamily(listFamily);
	}
	
	@Override
	public void sendDataAckSync() {

		List<Object[]> listDataAckSync = familyRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		familyService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		familyService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void sendUpdatedData(){
		System.out.println("Send Updated Data");
		
		
		// ambil last sync sesuai dengan modul dan company
		List<HistorySync> listHistory = historySyncService.findByModuleNameAndCompanyId("family", 1L);
		Date lastSync = new Date();
		if(listHistory != null && listHistory.size() > 0) {
			HistorySync objHistorySync = listHistory.get(0);
			lastSync = objHistorySync.getLastSync();
		}
		Iterable<Family> listFamily = familyService.getAllDataForUpdate(lastSync);
		listFamily(listFamily);
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
		String urlQuery = this.instanceUrl + "/services/apexrest/GetIdbyExtId?SyncObject=HRPERFAMILY__C";
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
				System.out.println("Ext Id "+ extId);
				System.out.println("uuid " + uuid);
				familyService.updateExtIdByUUID(extId, uuid);
			}
			
		} catch (HttpClientErrorException ex) {
			System.out.println("Error HTTP Client " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());

		}
		
	}

}
