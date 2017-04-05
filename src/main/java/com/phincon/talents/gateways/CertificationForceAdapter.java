package com.phincon.talents.gateways;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Certification;
import com.phincon.talents.gateways.services.CertificationService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class CertificationForceAdapter extends ForceAdapter<Certification>{

	@Autowired
	CertificationService certificationService;
	
	public CertificationForceAdapter() {
		super();
		query = "select Id,Name,Certification_Date__c,Certification_Description__c,Certification_Expired__c,Certification_Full__c,Certification_No__c,Certification_Type__c,Full_Name__c,Principle__c,Year_Certified__c"
				+ " from HRPERCERTIFICATION__c limit 10";
	}
	
	@Override
	protected Certification convertMapResultToObject(Map<String, Object> mapResult){		
		String extId = (String) mapResult.get("Id");
		String name = (String) mapResult.get("Name");
		String strCertificationDate = (String) mapResult.get("Certification_Date__c");
		String certificationDescription = (String) mapResult.get("Certification_Description__c");
		String strCertificationExpired = (String) mapResult.get("Certification_Expired__c");
		String certificationFull = (String) mapResult.get("Certification_Full__c");
		String certificationNo = (String) mapResult.get("Certification_No__c");
		String certificationType = (String) mapResult.get("Certification_Type__c");
		//String fullName = (String) mapResult.get("Full_Name__c");
		String principle = (String) mapResult.get("Principle__c");
		String yearCertified = (String) mapResult.get("Year_Certified__c");
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
			
			certificationService.save(certDb);
			System.out.println("Success Save Certification");
		}
	}

}
