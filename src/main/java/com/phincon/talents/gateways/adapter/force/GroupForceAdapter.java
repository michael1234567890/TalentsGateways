package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Group;
import com.phincon.talents.gateways.services.GroupService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class GroupForceAdapter extends ForceAdapter<Group>{
	
	@Autowired
	GroupService groupService;
	
	public GroupForceAdapter(){
		super();
		query = "Group";
	}
	
	@Override
	protected Group convertMapResultToObject(Map<String, Object> mapResult){
		
		String extId = "";
		if(mapResult.get("Id") != null)
			extId = (String) mapResult.get("Id");
		else
			extId = null;
		
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
		
		String ownerId = "";
		if(mapResult.get("OwnerId") != null)
			ownerId = (String) mapResult.get("OwnerId");
		else
			ownerId = null;
		
		String patternCode = "";
		if(mapResult.get("Pattern_Code__c") != null)
			patternCode = (String) mapResult.get("Pattern_Code__c");
		else
			patternCode = null;
		
//		String patternNo = "";
//		if(mapResult.get("Pattern_No__c") != null)
//			patternNo = (String) mapResult.get("Pattern_No__c");
//		else
//			patternNo = null;
		
		Integer patternNo = null;
		if(mapResult.get("Pattern_No__c") != null)
			patternNo = (Integer) mapResult.get("Pattern_No__c");
		
//		String sequenceDayNo = "";
//		if(mapResult.get("Sequences_Day_No__c") != null)
//			sequenceDayNo = (String) mapResult.get("Sequences_Day_No__c");
//		else
//			sequenceDayNo = null;
		
		Integer sequenceDayNo = null;
		if(mapResult.get("Sequences_Day_No__c") != null)
			sequenceDayNo = (Integer) mapResult.get("Sequences_Day_No__c");
		
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
		
		Group group = new Group();
		group.setExtId(extId);
		group.setMasterCode(masterCode);
		group.setName(name);
		group.setOwnerId(ownerId);
		group.setPatternCode(patternCode);
		group.setPatternNo(patternNo);
		group.setSequenceDayNo(sequenceDayNo);
		group.setStartDate(startDate);
		group.setWorkingOnHoliday(workingOnHoliday);
		
		return group;
	}
	
	@Override
	public void saveListData(List<Group> listData,boolean isInit){
		for(Group e : listData){
			System.out.println("Group : " +e.getExtId());
			// check is id exist
			Group groupDb = groupService.findByExtId(e.getExtId());
			// if exist doing update
			if(groupDb == null){
				groupDb = new Group();
				groupDb.setCreatedDate(new Date());
			}
			groupDb.setExtId(e.getExtId());
			groupDb.setMasterCode(e.getMasterCode());
			groupDb.setName(e.getName());
			groupDb.setOwnerId(e.getOwnerId());
			groupDb.setPatternCode(e.getPatternCode());
			groupDb.setPatternNo(e.getPatternNo());
			groupDb.setSequenceDayNo(e.getSequenceDayNo());
			groupDb.setStartDate(e.getStartDate());
			groupDb.setWorkingOnHoliday(e.getWorkingOnHoliday());
			groupDb.setCompany(this.companyid);
			groupService.save(groupDb);
			System.out.println("Success Save Group");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<Group> listGroup = groupService.findNeedSync();
		if(listGroup != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Group group : listGroup){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Master_Code__c", group.getMasterCode());
				map.put("Name", group.getName());
				map.put("OwnerId", group.getOwnerId());
				map.put("Pattern_Code__c", group.getPatternCode());
				map.put("Pattern_No__c", group.getPatternNo());
				map.put("Sequences_Day_No__c", group.getSequenceDayNo());
				map.put("Start_Date__c", group.getStartDate());
				map.put("Working_On_Holiday__c", group.getWorkingOnHoliday());
				listMap.add(map);
				System.out.println("No " + (i+1) + " : " + group.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + "Task Already Sending");
			
		}
	}
}
