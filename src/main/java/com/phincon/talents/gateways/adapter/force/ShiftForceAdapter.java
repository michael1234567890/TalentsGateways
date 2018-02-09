package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Shift;
import com.phincon.talents.gateways.services.ShiftService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class ShiftForceAdapter extends ForceAdapter<Shift>{
	
	@Autowired
	ShiftService shiftService;
	
	public ShiftForceAdapter(){
		super();
		query = "Shift";
	}
	
	@Override
	protected Shift convertMapResultToObject(Map<String, Object> mapResult){
		
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
		
		String strStartDate = "";
		if(mapResult.get("Start_Date__c") != null)
			strStartDate = (String) mapResult.get("Start_Date__c");
		else
			strStartDate = null;

		Date startDate = null;
		if(strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);
		
		Integer startTimeHour = null;
		if(mapResult.get("Start_Time_Hour__c") != null)
			startTimeHour = (Integer) mapResult.get("Start_Time_Hour__c");
		
		Integer startTimeMinute = null;
		if(mapResult.get("Start_Time_Minute__c") != null)
			startTimeMinute = (Integer) mapResult.get("Start_Time_Minute__c");
		
		Boolean workingOnHoliday = null;
		if(mapResult.get("") != null)
			workingOnHoliday = (Boolean) mapResult.get("Working_On_Holiday__c");
		
		Shift shift = new Shift();
		shift.setExtId(extId);
		shift.setMasterCode(name);
		shift.setName(name);
		shift.setOwnerId(ownerId);
		shift.setStartDate(startDate);
		shift.setStartHour(startTimeHour);
		shift.setStartMinute(startTimeMinute);
		shift.setWorkingOnHoliday(workingOnHoliday);
		
		return shift;
	}
	
	@Override
	public void saveListData(List<Shift> listData, boolean isInit){
		for(Shift e : listData){
			System.out.println("Shift : " +e.getExtId());
			// check is id exist
			Shift shiftDb = shiftService.findByExtId(e.getExtId());
			// if exist doing update
			if(shiftDb == null){
				shiftDb = new Shift();
				shiftDb.setCreatedDate(new Date());
			}
			shiftDb.setExtId(e.getExtId());
			shiftDb.setMasterCode(e.getMasterCode());
			shiftDb.setName(e.getName());
			shiftDb.setOwnerId(e.getOwnerId());
			shiftDb.setStartDate(e.getStartDate());
			shiftDb.setStartHour(e.getStartHour());
			shiftDb.setStartMinute(e.getStartMinute());
			shiftDb.setWorkingOnHoliday(e.getWorkingOnHoliday());
			shiftDb.setCompany(this.companyid);
			shiftService.save(shiftDb);
			System.out.println("Success Save Shift");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<Shift> listShift = shiftService.findNeedSync();
		if(listShift != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Shift shift : listShift){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Master_Code__c", shift.getMasterCode());
				map.put("Name", shift.getName());
				map.put("OwnerId", shift.getOwnerId());
				map.put("Start_Date__c", shift.getStartDate());
				map.put("Start_Time_Hour__c", shift.getStartHour());
				map.put("Start_Time_Minute__c", shift.getStartMinute());
				map.put("Working_On_Holiday__c", shift.getWorkingOnHoliday());
				listMap.add(map);
				System.out.println("No " + (i+1) + " : " + shift.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + "Task Already Sending");
		}
	}
}
