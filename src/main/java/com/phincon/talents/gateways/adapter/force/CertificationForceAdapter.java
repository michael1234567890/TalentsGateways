package com.phincon.talents.gateways.adapter.force;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Certification;
import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.services.CertificationService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class CertificationForceAdapter extends ForceAdapter<Certification>{

	@Autowired
	CertificationService certificationService;
	
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
	public void saveListDate(List<Certification> listData){
		for(Certification e : listData){
			System.out.println("Certification : " + e.getExtId());
			// check is id is exist
			Certification certDb = certificationService.findByExtId(e.getExtId());
			// if exist doing update
			if(certDb == null){
				certDb = new Certification();
			}
			certDb.setExtId(e.getExtId());
			certDb.setName(e.getName());
			certDb.setDate(e.getDate());
			certDb.setDescription(e.getDescription());
			certDb.setExpired(e.getExpired());
			certDb.setFull(e.getFull());
			certDb.setNo(e.getNo());
			certDb.setType(e.getType());
			certDb.setPrinciple(e.getPrinciple());
			certDb.setYear(e.getYear());
			Employee employee = employeeService.findByExtId(e.getEmployeeExtId());
			if (employee != null) {
				certDb.setEmployee(employee);
				
			}
			certDb.setEmployeeExtId(e.getEmployeeExtId());
			certDb.setCreatedDate(new Date());
			certDb.setModifiedDate(new Date());
			certificationService.save(certDb);
			System.out.println("Success Save Certification");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<Certification> listCertification = certificationService.findAllExtIdNull();
		if(listCertification != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Certification certification : listCertification){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Id", certification.getId());
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
				System.out.println("No " + (i+1) + " : " + certification.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap);
			System.out.println(i + " Task Already Sending");
		}
	}
	


}
