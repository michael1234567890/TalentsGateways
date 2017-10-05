package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Pattern;
import com.phincon.talents.gateways.services.PatternService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class PatternForceAdapter extends ForceAdapter<Pattern> {
	
	@Autowired
	PatternService patternService;
	
	public PatternForceAdapter(){
		super();
		query = "Pattern";
	}
	
	@Override
	protected Pattern convertMapResultToObject(Map<String, Object> mapResult){
		
		String extId = "";
		if(mapResult.get("Id") != null)
			extId = (String) mapResult.get("Id");
		else
			extId = null;
		
		String lookupField = "";
		if(mapResult.get("Lookup_Field__c") != null)
			lookupField = (String) mapResult.get("Lookup_Field__c");
		else
			lookupField = null;
		
		String masterCode = "";
		if(mapResult.get("Master_Code__c") != null)
			masterCode = (String) mapResult.get("Master_Code__c");
		else
			masterCode = null;
		
		String name = "";
		if(mapResult.get("Name") != null)
			name = (String) mapResult.get("Name");
		else
			name = null;
		
//		String noDays = "";
//		if(mapResult.get("No_Days__c") != null)
//			noDays = (String) mapResult.get("No_Days__c");
//		else
//			noDays = null;
		Integer noDays = null;
		if(mapResult.get("No_Days__c") != null)
			noDays = (Integer) mapResult.get("No_Days__c");
		
		String ownerId = "";
		if(mapResult.get("OwnerId") != null)
			ownerId = (String) mapResult.get("OwnerId");
		else
			ownerId = null;
		
		Integer patternNo = null;
		if(mapResult.get("Pattern_No__c") != null)
			patternNo = (Integer) mapResult.get("Pattern_No__c");
		
		String shiftCode = "";
		if(mapResult.get("Shift_Code__c") != null)
			shiftCode = (String) mapResult.get("Shift_Code__c");
		else
			shiftCode = null;
		
		String strStartDate = "";
		if(mapResult.get("Start_Date__c") != null)
			strStartDate = (String) mapResult.get("Start_Date__c");
		else
			strStartDate = null;
		
		Date startDate = null;
		if(strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);
		
		Boolean workingOnHoliday = null;
		if(mapResult.get("Working_On_Holiday__c") != null)
			workingOnHoliday = (Boolean) mapResult.get("Working_On_Holiday__c");
		
		Pattern pattern = new Pattern();
		pattern.setExtId(extId);
		pattern.setLookupField(lookupField);
		pattern.setMasterCode(masterCode);
		pattern.setName(name);
		pattern.setNoDays(noDays);
		pattern.setOwnerId(ownerId);
		pattern.setPatternNo(patternNo);
		pattern.setShiftCode(shiftCode);
		pattern.setStartDate(startDate);
		pattern.setWorkingOnHoliday(workingOnHoliday);
				
		return pattern;
	}
	
	@Override
	public void saveListData(List<Pattern> listData,boolean isInit){
		for(Pattern e : listData){
			System.out.println("Pattern : " +e.getExtId());
			// check is id exist
			Pattern patternDb = patternService.findByExtId(e.getExtId());
			// if exist doing update
			if(patternDb == null){
				patternDb = new Pattern();
				patternDb.setCreatedDate(new Date());
			}
			patternDb.setExtId(e.getExtId());
			patternDb.setCompany(this.companyid);
			patternDb.setLookupField(e.getLookupField());
			patternDb.setMasterCode(e.getMasterCode());
			patternDb.setName(e.getName());
			patternDb.setNoDays(e.getNoDays());
			patternDb.setOwnerId(e.getOwnerId());
			patternDb.setPatternNo(e.getPatternNo());
			patternDb.setShiftCode(e.getShiftCode());
			patternDb.setStartDate(e.getStartDate());
			patternDb.setWorkingOnHoliday(e.getWorkingOnHoliday());
			
			patternService.save(patternDb);
			System.out.println("Success Save Pattern");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<Pattern> listPattern = patternService.findNeedSync();
		if(listPattern != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Pattern pattern : listPattern){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Lookup_Field__c", pattern.getLookupField());
				map.put("Master_Code__c", pattern.getMasterCode());
				map.put("Name", pattern.getName());
				map.put("No_Days__c", pattern.getNoDays());
				map.put("OwnerId", pattern.getOwnerId());
				map.put("Pattern_No__c", pattern.getPatternNo());
				map.put("Shift_Code__c", pattern.getShiftCode());
				map.put("Start_Date__c", pattern.getStartDate());
				map.put("Working_On_Holiday__c", pattern.getWorkingOnHoliday());
				listMap.add(map);
				System.out.println("No " + (i+1) + " : " + pattern.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + "Task Already Sending");
		}
	}
}
