package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.TMRequest;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.TMRequestService;
import com.phincon.talents.gateways.utils.ForceResponseGetId;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class TMRequestForceAdapter extends ForceAdapter<TMRequest> {

	@Autowired
	TMRequestService tmRequestService;

	@Autowired
	EmploymentService employmentService;

	public TMRequestForceAdapter() {
		super();
	}

	@Override
	protected TMRequest convertMapResultToObject(Map<String, Object> mapResult) {
		String extId = (String) mapResult.get("Id");
		Double amount = (Double) mapResult.get("Amount__c");

		String strEndDate = (String) mapResult.get("End_Date__c");
		Date endDate = null;
		String module = (String) mapResult.get("Module__c");
		String name = (String) mapResult.get("Name");
		String categoryTypeExtId = (String) mapResult.get("RecordTypeId");
		String employeeExtId = (String) mapResult.get("Employee_No__c");
		String requesterExtId = (String) mapResult.get("Requester__c");
		String strStartDate = (String) mapResult.get("Start_Date__c");
		Date startDate = null;
		String type = (String) mapResult.get("Transaction_Type__c");
		String status = (String) mapResult.get("Last_Status__c");
		String strRequestDate = (String) mapResult.get("Request_Date__c");
		Date requestDate = null;
	      
		
		if (strEndDate != null)
			endDate = Utils.convertStringToDate(strEndDate);
		if (strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);
		if (strRequestDate != null)
			requestDate = Utils.convertStringToDate(strRequestDate);
		
		/*String categoryType = null;
		if (categoryTypeId != null) {
			Map<String, Object> recordType = (Map<String, Object>) mapResult
					.get("RecordType");
			if (recordType != null)
				categoryType = (String) recordType.get("Name");
		}*/
		

		TMRequest obj = new TMRequest();
		obj.setExtId(extId);
		obj.setAmount(amount);
		// obj.setCategoryType(categoryType);
		obj.setEndDate(endDate);
		obj.setExtId(extId);
		obj.setReqNo(name);
		obj.setRequestDate(requestDate);
		obj.setRequesterEmploymentExtId(requesterExtId);
		obj.setEmploymentExtId(employeeExtId);
		obj.setCategoryTypeExtId(categoryTypeExtId);
		obj.setModule(module);
		obj.setStartDate(startDate);
		// obj.setStartTimeBreak(startTimeBreak);
		obj.setStatus(status);
//		obj.setSubtituteToEmp(subtituteToEmp);
//		obj.setSubtituteToReason(subtituteToReason);
		// obj.setTotalDay(totalDay);
//		obj.setTotalWorkDay(totalWorkDay);
		
		obj.setType(type);
		return obj;
	}

	@Override
	public void saveListData(List<TMRequest> listData, boolean isNull) {
		for (TMRequest e : listData) {
			TMRequest obj = tmRequestService.findByExtId(e.getExtId());

			if (obj == null) {
				obj = new TMRequest();
				obj.setCreatedDate(new Date());
				obj.setCreatedBy("Talents Gateway");
				obj.setExtId(e.getExtId());
			}

			if(isNull)
				obj.setAckSync(false);
			obj.setAmount(e.getAmount());
			// obj.setCategoryType(categoryType);
			obj.setEndDate(e.getEndDate());
			obj.setReqNo(e.getReqNo());
			obj.setRequestDate(e.getRequestDate());
			obj.setRequesterEmploymentExtId(e.getRequesterEmploymentExtId());
			obj.setEmploymentExtId(e.getEmploymentExtId());
			obj.setCategoryTypeExtId(e.getCategoryTypeExtId());
			obj.setModule(e.getModule());
			obj.setStartDate(e.getStartDate());
			// obj.setStartTimeBreak(startTimeBreak);
			obj.setStatus(e.getStatus());
//			obj.setSubtituteToEmp(subtituteToEmp);
//			obj.setSubtituteToReason(subtituteToReason);
			// obj.setTotalDay(totalDay);
//			obj.setTotalWorkDay(totalWorkDay);
			
			obj.setType(e.getType());

			Employment employment = null;
			if (e.getEmploymentExtId() != null) {
				employment = employmentService.findByExtId(e.getEmploymentExtId());
				if (employment != null)
					obj.setEmployment(employment.getId());
			}
			
			Employment requester = null;
			if (e.getRequesterEmploymentExtId() != null) {
				requester = employmentService.findByExtId(e.getRequesterEmploymentExtId());
				if (requester != null)
					obj.setRequesterEmployment(requester.getId());
			}
			
			obj.setCompany(this.companyid);
			obj.setModifiedDate(new Date());
			obj.setModifiedBy("Talents Gateway");
			tmRequestService.save(obj);
			System.out.println("TMRequest Save");
		}
	}

	@Override
	public void sendNewData() {
		// get data with ext id is null
		System.out.println("Send New Data");
		List<TMRequest> listFamily = tmRequestService.findNeedSync();
		listRequest(listFamily);
	}
	
	public void listRequest(List<TMRequest> listRequest){
		if (listRequest != null) {
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (TMRequest request : listRequest) {
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("id", family.getId());
				if(request.getExtId() != null)
					map.put("Id", request.getExtId());
				
				map.put("ExtId__c", request.getUuid());
				map.put("Amount__c", request.getAmount());
				map.put("Module__c", request.getModule());
				
				// recordtypeid di force ini apa ? dikasih categori ext id masih gak mau
				
				map.put("RecordTypeId", request.getCategoryTypeExtId());
				
				map.put("Last_Status__c", request.getStatus());
				map.put("Transaction_Type__c", request.getType());
				map.put("Request_Date__c", request.getRequestDate());
				map.put("Employee_No__c", request.getEmploymentExtId());
				map.put("Requester__c", request.getRequesterEmploymentExtId());
				//Start_Date__c /datetime
				map.put("Start_Date__c", request.getStartDate());
				//Start_Time_Break__c / datetime
				map.put("Start_Time_Break__c", request.getStartTimeBreak());
				//Total_Day__c /Number(3, 2) 
				map.put("Total_Day__c", request.getTotalDay());
				//Total_Work_Day__c /Number(3, 2) 
				map.put("Total_Work_Day__c", request.getTotalWorkDay());
				//End_Date__c / datetime
				map.put("End_Date__c", request.getEndDate());
				//End_Time_Break__c /datetime
				map.put("End_Time_Break__c", request.getEndTimeBreak());
				//Destination__c
				map.put("Destination__c", request.getDestination());
				//Origination__c
				map.put("Origination__c", request.getOrigin());
				//Subtitute_To_Employee_No__c	Lookup(Employment) 
				map.put("Overtime_In__c", request.getOvertimeIn());
				map.put("Overtime_Out__c", request.getOvertimeOut());
				map.put("End_Date_Time__c", request.getAttendanceOutTime()); // edit_in_time 
				map.put("Start_Date_Time__c", request.getAttendanceInTime()); // edit_out_time 
				map.put("Transaction_Code__c", request.getTypeDesc()); // reason
				map.put("Request_remark__c", request.getType()); // remark (value nya sama kaya reason sesuai permintaan pak yadi udah bahas bu lidya)
				map.put("Start_Date_In_Time__c", request.getStartDateInTime());
				map.put("End_Date_In_Time__c", request.getEndDateInTime());
				map.put("Start_Date_Out_Time__c", request.getStartDateOutTime());
				map.put("End_Date_Out_Time__c", request.getEndDateOutTime());
				map.put("Bank_Account__c", request.getBankAccount());
				map.put("Bank_Name__c", request.getBankName());
				map.put("Account_Name__c", request.getAccountName());
				
				if(request.getCategoryType().equalsIgnoreCase("Attendance Edit"));
					map.put("Process_flag__c", 0);
				
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
		String urlQuery = this.instanceUrl + "/services/apexrest/GetIdbyExtId?SyncObject=tmtranrequest";
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
				tmRequestService.updateExtIdByUUID(extId, uuid);
			}
			
		} catch (HttpClientErrorException ex) {
			System.out.println("Error HTTP Client " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());

		}
		
	}
}
