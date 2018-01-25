package com.phincon.talents.gateways.adapter.force;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.phincon.talents.gateways.model.AtempDaily;
import com.phincon.talents.gateways.model.HistorySync;
import com.phincon.talents.gateways.model.SyncSettings;
import com.phincon.talents.gateways.repository.AtempDailyRepository;
import com.phincon.talents.gateways.repository.HistorySyncRepository;
import com.phincon.talents.gateways.repository.SyncSettingsRepository;
import com.phincon.talents.gateways.services.AtempDailyService;
import com.phincon.talents.gateways.utils.ForceResponse;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class AtempDailyForceAdapter extends ForceAdapter<AtempDaily>{

	@Autowired
	AtempDailyService atempDailyService;
	
	@Autowired
	AtempDailyRepository atempDailyRepository;
	
	@Autowired
	HistorySyncRepository historySyncRepository;
	
	@Autowired
	SyncSettingsRepository syncSettingsRepository;
	
	public AtempDailyForceAdapter(){
		super();
		query = "atempdaily";
	}
	
	@Override
	protected AtempDaily convertMapResultToObject(Map<String, Object> mapResult){
		
		String extId = "";
		if(mapResult.get("Id") != null)
			extId = (String) mapResult.get("Id");
		else
			extId = null;
		
		Double acceptedOvertimeIn = null;
		if(mapResult.get("Ot_Accepted_In__c") != null)
			acceptedOvertimeIn = (Double) mapResult.get("Ot_Accepted_In__c");
		
		Double acceptedOvertimeOut = null;
		if(mapResult.get("Ot_Accepted_Out__c") != null)
			acceptedOvertimeOut = (Double) mapResult.get("Ot_Accepted_Out__c");
		
		Double acceptedOvertimeOutFromDay = null;
		if(mapResult.get("Accepted_Overtime_Out_Fromday__c") != null)
			acceptedOvertimeOutFromDay = (Double) mapResult.get("Accepted_Overtime_Out_Fromday__c");
		
		String strActualInTime = "";
		if(mapResult.get("Actual_In_Time__c") != null)
			strActualInTime = (String) mapResult.get("Actual_In_Time__c");
		else
			strActualInTime = null;
		
		Date actualInTime = null;
		if(strActualInTime != null)
			actualInTime = Utils.converStringToDatetime(strActualInTime);
		
		String strActualInTimeGmt = "";
		if(mapResult.get("Actual_In_Time_GMT__c") != null)
			strActualInTimeGmt = (String) mapResult.get("Actual_In_Time_GMT__c");
		else 
			strActualInTimeGmt = null;
		
		Date actualInTimeGmt = null;
		if(strActualInTimeGmt != null)
			actualInTimeGmt = Utils.converStringToDatetime(strActualInTimeGmt);
		
		String strActualOutTime = "";
		if(mapResult.get("Actual_Out_Time__c") != null)
			strActualOutTime = (String) mapResult.get("Actual_Out_Time__c");
		else
			strActualOutTime = null;
		
		Date actualOutTime = null;
		if(strActualOutTime != null)
			actualOutTime = Utils.converStringToDatetime(strActualOutTime);
		
		String cardNo = "";
		if(mapResult.get("Card_No__c") != null)
			cardNo = (String) mapResult.get("Card_No__c");
		
		String compareField = "";
		if(mapResult.get("Compare_Field__c") != null)
			compareField = (String) mapResult.get("Compare_Field__c");
		
		String dayType = "";
		if(mapResult.get("Day_Type__c") != null)
			dayType = (String) mapResult.get("Day_Type__c");
		
		String strEditInTime = "";
		if(mapResult.get("Edit_In_Time__c") != null)
			strEditInTime = (String) mapResult.get("Edit_In_Time__c");
		else
			strEditInTime = null;
		
		Date editInTime = null;
		if(strEditInTime != null)
			editInTime = Utils.converStringToDatetime(strEditInTime);
		
		String strEditInTimeLpad = "";
		if(mapResult.get("Edit_In_Time_LPAD__c") != null)
			strEditInTimeLpad = (String) mapResult.get("Edit_In_Time_LPAD__c");
		else 
			strEditInTimeLpad = null;
		
		Date editInTimeLpad = null;
		if(strEditInTimeLpad != null)
			editInTimeLpad = Utils.converStringToDatetime(strEditInTimeLpad);
		
		String strEditOutTime = "";
		if(mapResult.get("Edit_Out_Time__c") != null)
			strEditOutTime = (String) mapResult.get("Edit_Out_Time__c");
		else
			strEditOutTime = null;
		
		Date editOutTime = null;
		if(strEditOutTime != null)
			editOutTime = Utils.converStringToDatetime(strEditOutTime);
		
		String strEditOutTimeLpad = "";
		if(mapResult.get("Edit_Out_Time_LPAD__c") != null)
			strEditOutTimeLpad = (String) mapResult.get("Edit_Out_Time_LPAD__c");
		else
			strEditOutTimeLpad = null;
		
		Date editOutTimeLpad = null;
		if(strEditOutTimeLpad != null)
			editOutTimeLpad = Utils.converStringToDatetime(strEditOutTimeLpad);
		
		String employeeNo = "";
		if(mapResult.get("Employee_No__c") != null)
			employeeNo = (String) mapResult.get("Employee_No__c");
		else
			employeeNo = null;
		
		String strEndOvertimeLimit = "";
		if(mapResult.get("End_Ovt_Limit__c") != null)
			strEndOvertimeLimit = (String) mapResult.get("End_Ovt_Limit__c");
		else
			strEndOvertimeLimit = null;
		
		Date endOvertimeLimit = null;
		if(strEndOvertimeLimit != null)
			endOvertimeLimit = Utils.converStringToDatetime(strEndOvertimeLimit);
						
		String strEndRangeIn = "";
		if(mapResult.get("End_Range_In__c") != null)
			strEndRangeIn = (String) mapResult.get("End_Range_In__c");
		else
			strEndRangeIn = null;
		
		Date endRangeIn = null;
		if(strEndRangeIn != null)
			endRangeIn = Utils.converStringToDatetime(strEndRangeIn);
		
		String strEndRangeOut = "";
		if(mapResult.get("End_Range_Out__c") != null)
			strEndRangeOut = (String) mapResult.get("End_Range_Out__c");
		else
			strEndRangeOut = null;
		
		Date endRangeOut = null;
		if(strEndRangeOut != null)
			endRangeIn = Utils.converStringToDatetime(strEndRangeOut);
		
		Double indexOvertimeIn = null;
		if(mapResult.get("OT_IndexOT_In__c") != null)
			indexOvertimeIn = (Double) mapResult.get("OT_IndexOT_In__c");
		
		Double indexOvertimeOut = null;
		if(mapResult.get("Ovt_Index_Out_Formula__c") != null)
			indexOvertimeOut = (Double) mapResult.get("Ovt_Index_Out_Formula__c");
		
		Double indexOvertimeOut1 = null;
		if(mapResult.get("OT_IndexOT_Out_1__c") != null)
			indexOvertimeOut1 = (Double) mapResult.get("OT_IndexOT_Out_1__c");
		
		Double indexOvertimeOut2 = null;
		if(mapResult.get("OT_IndexOT_Out_2__c") != null)
			indexOvertimeOut2 = (Double) mapResult.get("OT_IndexOT_Out_2__c");
		
		Double indexOvertimeOut3 = null;
		if(mapResult.get("OT_IndexOT_Out_3__c") != null)
			indexOvertimeOut3 = (Double) mapResult.get("OT_IndexOT_Out_3__c");
		
		Double indexOvertimeOut4 = null;
		if(mapResult.get("OT_IndexOT_Out_4__c") != null)
			indexOvertimeOut4 = (Double) mapResult.get("OT_IndexOT_Out_4__c");
		
		Double indexOvertimeOutFromday = null;
		if(mapResult.get("Index_Overtime_Out_Fromday__c") != null)
			indexOvertimeOutFromday = (Double) mapResult.get("Index_Overtime_Out_Fromday__c");
		
		Double indexOvertimeOutFromdayFormula = null;
		if(mapResult.get("Index_Overtime_Out_Fromday_Formula__c") != null)
			indexOvertimeOutFromdayFormula = (Double) mapResult.get("Index_Overtime_Out_Fromday_Formula__c");
		
		String strInTime = "";
		if(mapResult.get("In_Time__c") != null)
			strInTime = (String) mapResult.get("In_Time__c");
		else
			strInTime = null;
		
		Date inTime = null;
		if(strInTime != null)
			inTime = Utils.converStringToDatetime(strInTime);
		
		Integer inTimeExtra = null;
		if(mapResult.get("In_Time_Ext__c") != null)
			inTimeExtra = (Integer) mapResult.get("In_Time_Ext__c");
		
		Integer inTimeExtraFormula = null;
		if(mapResult.get("In_Time_Extra_Formula__c") != null)
			inTimeExtraFormula = (Integer) mapResult.get("In_Time_Extra_Formula__c");
		
		Integer lateInTime = null;
		if(mapResult.get("Late_In_Time__c") != null)
			lateInTime = (Integer) mapResult.get("Late_In_Time__c");
		
		String locationIn = "";
		if(mapResult.get("Location_In__c") != null)
			locationIn = (String) mapResult.get("Location_In__c");
		else
			locationIn = null;
		
		String locationOut = "";
		if(mapResult.get("Location_Out__c") != null)
			locationOut = (String) mapResult.get("Location_Out__c");
		else
			locationOut = null;
		
		String machineNo = "";
		if(mapResult.get("Machine_No__c") != null)
			machineNo = (String) mapResult.get("Machine_No__c");
		else
			machineNo = null;
		
		String machineTerminal = "";
		if(mapResult.get("Machine_Terminal__c") != null)
			machineTerminal = (String) mapResult.get("Machine_Terminal__c");
		else
			machineTerminal = null;
		
		Double mealOvertime = null;
		if(mapResult.get("Meal_Ot2__c") != null)
			mealOvertime = (Double) mapResult.get("Meal_Ot2__c");
		
		Double mealOvertimeHd = null;
		if(mapResult.get("MealOt_HD2__c") != null)
			mealOvertimeHd = (Double) mapResult.get("MealOt_HD2__c");
		
		Boolean MTSWP = null;
		if(mapResult.get("MTSWP__c") != null)
			MTSWP = (Boolean) mapResult.get("MTSWP__c");
		
		String strOutEndTimeMin = "";
		if(mapResult.get("Out_End_Time_Min_Date__c") != null)
			strOutEndTimeMin = (String) mapResult.get("Out_End_Time_Min_Date__c");
		else
			strOutEndTimeMin = null;
		
		Date outEndTimeMin = null;
		if(strOutEndTimeMin != null)
			outEndTimeMin = Utils.converStringToDatetime(strOutEndTimeMin);
		
		String strOutEndTimeMax = "";
		if(mapResult.get("Out_End_Time_Max_Date__c") != null)
			strOutEndTimeMax = (String) mapResult.get("Out_End_Time_Max_Date__c");
		else
			strOutEndTimeMax = null;
		
		Date outEndTimeMax = null;
		if(strOutEndTimeMax != null)
			outEndTimeMax = Utils.converStringToDatetime(strOutEndTimeMax);
		
		String strOutTime = "";
		if(mapResult.get("Out_Time__c") != null)
			strOutTime = (String) mapResult.get("Out_Time__c");
		else
			strOutTime = null;
		
		Date outTime = null;
		if(strOutTime != null)
			outTime = Utils.converStringToDatetime(strOutTime);				
		
		Integer outTimeExtra = null;
		if(mapResult.get("Out_Time_Ext__c") != null)
			outTimeExtra = (Integer) mapResult.get("Out_Time_Ext__c");
		
		Integer outTimeExtraFormula = null;
		if(mapResult.get("Out_Time_Extra_Formula__c") != null)
			outTimeExtraFormula = (Integer) mapResult.get("Out_Time_Extra_Formula__c");
		
		Integer outTimeExtraOvertime = null;
		if(mapResult.get("Out_Time_Extra_Ovt__c") != null)
			outTimeExtraOvertime = (Integer) mapResult.get("Out_Time_Extra_Ovt__c");
		
		Double overtimeAutoIn = null;
		if(mapResult.get("Ovt_Auto_In__c") != null)
			overtimeAutoIn = (Double) mapResult.get("Ovt_Auto_In__c");
		
		Double overtimeAutoOut = null;
		if(mapResult.get("Overtime_Auto_Out__c") != null)
			overtimeAutoOut = (Double) mapResult.get("Overtime_Auto_Out__c");
		
		String strOvertimeBreakEnd = "";
		if(mapResult.get("Overtime_Break_End__c") != null)
			strOvertimeBreakEnd = (String) mapResult.get("Overtime_Break_End__c");
		else
			strOvertimeBreakEnd = null;
		
		Date overtimeBreakEnd = null;
		if(strOvertimeBreakEnd != null)
			overtimeBreakEnd = Utils.converStringToDatetime(strOvertimeBreakEnd);
		
		String strOvertimeBreakStart = "";
		if(mapResult.get("Overtime_Break_Start__c") != null)
			strOvertimeBreakStart = (String) mapResult.get("Overtime_Break_Start__c");
		else
			strOvertimeBreakStart = null;
		
		Date overtimeBreakStart = null;
		if(strOvertimeBreakStart != null)
			overtimeBreakStart = Utils.converStringToDatetime(strOvertimeBreakStart);
		
		String overtimeFactorCode = "";
		if(mapResult.get("Ot_Factor_Code__c") != null)
			overtimeFactorCode = (String) mapResult.get("Ot_Factor_Code__c");
		else
			overtimeFactorCode = null;
		
		Integer overtimeOutTime = null;
		if(mapResult.get("Overtime_Out_Time__c") != null)
			overtimeOutTime = (Integer) mapResult.get("Overtime_Out_Time__c");
		
		Boolean PLCPT = null;
		if(mapResult.get("PLCPT__c") != null)
			PLCPT = (Boolean) mapResult.get("PLCPT__c");
		
		Boolean processFlag = null;
		if(mapResult.get("Process_Flag__c") != null)
			processFlag = (Boolean) mapResult.get("Process_Flag__c");
		
		Boolean PTSWP = null;
		if(mapResult.get("PTSWP__c") != null)
			PTSWP = (Boolean) mapResult.get("PTSWP__c");
		
		Integer quickOutTime = null;
		if(mapResult.get("Quick_Out_Time__c") != null)
			quickOutTime = (Integer) mapResult.get("Quick_Out_Time__c");
		
		String reason = "";
		if(mapResult.get("Reason__c") != null)
			reason = (String) mapResult.get("Reason__c");
		else 
			reason = null;
		
		String remark = "";
		if(mapResult.get("Remark__c") != null)
			remark = (String) mapResult.get("Remark__c");
		else 
			remark = null;
		
		String editReason = "";
		if(mapResult.get("Edit_Reason__c") != null)
			editReason = (String) mapResult.get("Edit_Reason__c");
		else 
			editReason = null;
		
		String editRemark = "";
		if(mapResult.get("Edit_Remark__c") != null)
			editRemark = (String) mapResult.get("Edit_Remark__c");
		else 
			editRemark = null;
		
		Integer requestOvertimeIn = null;
		if(mapResult.get("Req_Ot_In__c") != null)
			requestOvertimeIn = (Integer) mapResult.get("Req_Ot_In__c");
		
		Integer requestOvertimeOut = null;
		if(mapResult.get("Req_Ot_Out__c") != null)
			requestOvertimeOut = (Integer) mapResult.get("Req_Ot_Out__c");
		
		String strShiftBreakEnd = "";
		if(mapResult.get("Shift_Break_End__c") != null)
			strShiftBreakEnd = (String) mapResult.get("Shift_Break_End__c");
		else
			strShiftBreakEnd = null;
		
		Date shiftBreakEnd = null;
		if(strShiftBreakEnd != null)
			shiftBreakEnd = Utils.converStringToDatetime(strShiftBreakEnd);
		
		String strShiftBreakStart = "";
		if(mapResult.get("Shift_Break_Start__c") != null)
			strShiftBreakStart = (String) mapResult.get("Shift_Break_Start__c");
		else
			strShiftBreakStart = null;
		
		Date shiftBreakStart = null;
		if(strShiftBreakStart != null)
			shiftBreakStart = Utils.converStringToDatetime(strShiftBreakStart);
		
		String shiftCode = "";
		if(mapResult.get("Shift_Code__c") != null)
			shiftCode = (String) mapResult.get("Shift_Code__c");
		else
			shiftCode = null;
		
		String strShiftOvertimeEnd = "";
		if(mapResult.get("Shift_Overtime_End__c") != null)
			strShiftOvertimeEnd = (String) mapResult.get("Shift_Overtime_End__c");
		else
			strShiftOvertimeEnd = null;
		
		Date shiftOvertimeEnd = null;
		if(strShiftOvertimeEnd != null)
			shiftOvertimeEnd = Utils.converStringToDatetime(strShiftOvertimeEnd);
		
		String strShiftOvertimeStart = "";
		if(mapResult.get("Shift_Overtime_Start__c") != null)
			strShiftOvertimeStart = (String) mapResult.get("Shift_Overtime_Start__c");
		else
			strShiftOvertimeStart = null;
		
		Date shiftOvertimeStart = null;
		if(strShiftOvertimeStart != null)
			shiftOvertimeStart = Utils.converStringToDatetime(strShiftOvertimeStart);
		
		String strStartOvertimeLimit = "";
		if(mapResult.get("Start_Ovt_Limit__c") != null)
			strStartOvertimeLimit = (String) mapResult.get("Start_Ovt_Limit__c");
		else
			strStartOvertimeLimit = null;
		
		Date startOvertimeLimit = null;
		if(strStartOvertimeLimit != null)
			startOvertimeLimit = Utils.converStringToDatetime(strStartOvertimeLimit);
		
		String strStartRangeIn = "";
		if(mapResult.get("Start_Range_In__c") != null)
			strStartRangeIn = (String) mapResult.get("Start_Range_In__c");
		else
			strStartRangeIn = null;
		
		Date startRangeIn = null;
		if(strStartRangeIn != null)
			startRangeIn = Utils.converStringToDatetime(strStartRangeIn);
		
		String strStartRangeOut = "";
		if(mapResult.get("Start_Range_Out__c") != null)
			strStartRangeOut = (String) mapResult.get("Start_Range_Out__c");
		else
			strStartRangeOut = null;
		
		Date startRangeOut = null;
		if(strStartRangeOut != null)
			startRangeOut = Utils.converStringToDatetime(strStartRangeOut);
		
		Boolean TLHDR = null;
		if(mapResult.get("TLHDR__c") != null)
			TLHDR = (Boolean) mapResult.get("TLHDR__c");
		
		Double totalOvertimeDay = null;
		if(mapResult.get("Ot_Total_Day__c") != null)
			totalOvertimeDay = (Double) mapResult.get("Ot_Total_Day__c");
		
		Double totalOvertimeIndex = null;
		if(mapResult.get("Total_Overtime_Index__c") != null)
			totalOvertimeIndex = (Double) mapResult.get("Total_Overtime_Index__c");
		
		Double totalOvertimeIndexFormula = null;
		if(mapResult.get("Total_Ovt_Index__c") != null)
			totalOvertimeIndex = (Double) mapResult.get("Total_Ovt_Index__c");
		
		String transactionCode = "";
		if(mapResult.get("Attend_Code__c") != null)
			transactionCode = (String) mapResult.get("Attend_Code__c");
		else
			transactionCode = null;
		
		Double transportOvertime = null;
		if(mapResult.get("Transport_Ot2__c") != null)
			transportOvertime = (Double) mapResult.get("Transport_Ot2__c");
		
		Double transportOvertimeHd = null;
		if(mapResult.get("Transport_Ot_HD2__c") != null)
			transportOvertimeHd = (Double) mapResult.get("Transport_Ot_HD2__c");
		
		String transactionRefNo = "";
		if(mapResult.get("Request_Tran_RefNo__c") != null)
			transactionRefNo = (String) mapResult.get("Request_Tran_RefNo__c");
		else
			transactionRefNo = null;
		
		String strWorkDate = "";
		if(mapResult.get("Work_Date__c") != null)
			strWorkDate = (String) mapResult.get("Work_Date__c");
		else 
			strWorkDate = null;
		
		Date workDate = null;
		if(strWorkDate != null)
			workDate = Utils.convertStringToDate(strWorkDate);
		
		AtempDaily atempDaily = new AtempDaily();
		atempDaily.setExtId(extId);
		atempDaily.setAcceptedOvertimeIn(acceptedOvertimeIn);
		atempDaily.setAcceptedOvertimeOut(acceptedOvertimeOut);
		atempDaily.setAcceptedOvertimeOutFromDay(acceptedOvertimeOutFromDay);
		atempDaily.setActualInTime(actualInTime);
		atempDaily.setActualInTimeGmt(actualInTimeGmt);
		atempDaily.setActualOutTime(actualOutTime);
		atempDaily.setCardNo(cardNo);
		atempDaily.setCompareField(compareField);
		atempDaily.setDayType(dayType);
		atempDaily.setEditInTime(editInTime);
		atempDaily.setEditInTimeLpad(editInTimeLpad);
		atempDaily.setEditOutTime(editOutTime);
		atempDaily.setEditOutTimeLpad(editOutTimeLpad);
		atempDaily.setEmployeeNo(employeeNo);
		atempDaily.setEndOvertimeLimit(endOvertimeLimit);
		atempDaily.setEndRangeIn(endRangeIn);
		atempDaily.setEndRangeOut(endRangeOut);
		atempDaily.setIndexOvertimeIn(indexOvertimeIn);
		atempDaily.setIndexOvertimeOut(indexOvertimeOut);
		atempDaily.setIndexOvertimeOut1(indexOvertimeOut1);
		atempDaily.setIndexOvertimeOut2(indexOvertimeOut2);
		atempDaily.setIndexOvertimeOut3(indexOvertimeOut3);
		atempDaily.setIndexOvertimeOut4(indexOvertimeOut4);
		atempDaily.setIndexOvertimeOutFromday(indexOvertimeOutFromday);
		atempDaily.setIndexOvertimeOutFromdayFormula(indexOvertimeOutFromdayFormula);
		atempDaily.setInTime(inTime);
		atempDaily.setInTimeExtra(inTimeExtra);
		atempDaily.setInTimeExtraFormula(inTimeExtraFormula);
		atempDaily.setLateInTime(lateInTime);
		atempDaily.setLocationIn(locationIn);
		atempDaily.setLocationOut(locationOut);
		atempDaily.setMachineNo(machineNo);
		atempDaily.setMachineTerminal(machineTerminal);
		atempDaily.setMealOvertime(mealOvertime);
		atempDaily.setMealOvertimeHd(mealOvertimeHd);
		atempDaily.setMTSWP(MTSWP);
		atempDaily.setOutEndTimeMin(outEndTimeMin);
		atempDaily.setOutEndTimeMax(outEndTimeMax);
		atempDaily.setOutTime(outTime);
		atempDaily.setOutTimeExtra(outTimeExtra);
		atempDaily.setOutTimeExtraFormula(outTimeExtraFormula);
		atempDaily.setOutTimeExtraOvertime(outTimeExtraOvertime);
		atempDaily.setOvertimeAutoIn(overtimeAutoIn);
		atempDaily.setOvertimeAutoOut(overtimeAutoOut);
		atempDaily.setOvertimeBreakEnd(overtimeBreakEnd);
		atempDaily.setOvertimeBreakStart(overtimeBreakStart);
		atempDaily.setOvertimeFactorCode(overtimeFactorCode);
		atempDaily.setOvertimeOutTime(overtimeOutTime);
		atempDaily.setPLCPT(PLCPT);
		atempDaily.setProcessFlag(processFlag);
		atempDaily.setPTSWP(PTSWP);
		atempDaily.setQuickOutTime(quickOutTime);
		atempDaily.setReason(reason);
		atempDaily.setRemark(remark);
		atempDaily.setEditReason(editReason);
		atempDaily.setEditRemark(editRemark);
		atempDaily.setRequestOvertimeIn(requestOvertimeIn);
		atempDaily.setRequestOvertimeOut(requestOvertimeOut);
		atempDaily.setShiftBreakEnd(shiftBreakEnd);
		atempDaily.setShiftBreakStart(shiftBreakStart);
		atempDaily.setShiftCode(shiftCode);
		atempDaily.setShiftOvertimeEnd(shiftOvertimeEnd);
		atempDaily.setShiftOvertimeStart(shiftOvertimeStart);
		atempDaily.setStartOvertimeLimit(startOvertimeLimit);
		atempDaily.setStartRangeIn(startRangeIn);
		atempDaily.setStartRangeOut(startRangeOut);
		atempDaily.setTLHDR(TLHDR);
		atempDaily.setTotalOvertimeDay(totalOvertimeDay);
		atempDaily.setTotalOvertimeIndex(totalOvertimeIndex);
		atempDaily.setTotalOvertimeIndexFormula(totalOvertimeIndexFormula);
		atempDaily.setTransactionCode(transactionCode);
		atempDaily.setTransportOvertime(transportOvertime);
		atempDaily.setTransportOvertimeHd(transportOvertimeHd);
		atempDaily.setTransactionRefNo(transactionRefNo);
		atempDaily.setWorkDate(workDate);
		
		
		return atempDaily;
	}
	
	@Override
	public void saveListData(List<AtempDaily> listData, boolean isInit){
		for(AtempDaily e : listData){
			System.out.println("AtEmpDaily : " +e.getExtId());
			// check is id exist
			AtempDaily atempDailyDb = atempDailyService.findByExtId(e.getExtId());
			// if exist doing update
			if(atempDailyDb == null){
				atempDailyDb = new AtempDaily();
				atempDailyDb.setCreatedDate(new Date());
			}
			
			if(isInit)
				atempDailyDb.setAckSync(false);
			
			atempDailyDb.setExtId(e.getExtId());
			atempDailyDb.setAcceptedOvertimeIn(e.getAcceptedOvertimeIn());
			atempDailyDb.setAcceptedOvertimeOut(e.getAcceptedOvertimeOut());
			atempDailyDb.setAcceptedOvertimeOutFromDay(e.getAcceptedOvertimeOutFromDay());
			atempDailyDb.setActualInTime(e.getActualInTime());
			atempDailyDb.setActualInTimeGmt(e.getActualInTimeGmt());
			atempDailyDb.setActualOutTime(e.getActualOutTime());
			atempDailyDb.setCardNo(e.getCardNo());
			atempDailyDb.setCompareField(e.getCompareField());
			atempDailyDb.setDayType(e.getDayType());
			atempDailyDb.setEditInTime(e.getEditInTime());
			atempDailyDb.setEditInTimeLpad(e.getEditInTimeLpad());
			atempDailyDb.setEditOutTime(e.getEditOutTime());
			atempDailyDb.setEditOutTimeLpad(e.getEditOutTimeLpad());
			atempDailyDb.setEmployeeNo(e.getEmployeeNo());
			atempDailyDb.setEndOvertimeLimit(e.getEndOvertimeLimit());
			atempDailyDb.setEndRangeIn(e.getEndRangeIn());
			atempDailyDb.setEndRangeOut(e.getEndRangeOut());
			atempDailyDb.setIndexOvertimeIn(e.getIndexOvertimeIn());
			atempDailyDb.setIndexOvertimeOut1(e.getIndexOvertimeOut1());
			atempDailyDb.setIndexOvertimeOut2(e.getIndexOvertimeOut2());
			atempDailyDb.setIndexOvertimeOut3(e.getIndexOvertimeOut3());
			atempDailyDb.setIndexOvertimeOut4(e.getIndexOvertimeOut4());
			atempDailyDb.setIndexOvertimeOutFromday(e.getIndexOvertimeOutFromday());
			atempDailyDb.setIndexOvertimeOutFromdayFormula(e.getIndexOvertimeOutFromdayFormula());
			atempDailyDb.setInTime(e.getInTime());
			atempDailyDb.setInTimeExtra(e.getInTimeExtra());
			atempDailyDb.setInTimeExtraFormula(e.getInTimeExtraFormula());
			atempDailyDb.setLateInTime(e.getLateInTime());
			atempDailyDb.setLocationIn(e.getLocationIn());
			atempDailyDb.setLocationOut(e.getLocationOut());
			atempDailyDb.setMachineNo(e.getMachineNo());
			atempDailyDb.setMachineTerminal(e.getMachineTerminal());
			atempDailyDb.setMealOvertime(e.getMealOvertime());
			atempDailyDb.setMealOvertimeHd(e.getMealOvertimeHd());
			atempDailyDb.setMTSWP(e.getMTSWP());
			atempDailyDb.setOutEndTimeMin(e.getOutEndTimeMin());
			atempDailyDb.setOutEndTimeMax(e.getOutEndTimeMax());
			atempDailyDb.setOutTime(e.getOutTime());
			atempDailyDb.setOutTimeExtra(e.getOutTimeExtra());
			atempDailyDb.setOutTimeExtraFormula(e.getOutTimeExtraFormula());
			atempDailyDb.setOutTimeExtraOvertime(e.getOutTimeExtraOvertime());
			atempDailyDb.setOutTimeExtraOvertime(e.getOutTimeExtraOvertime());
			atempDailyDb.setOvertimeAutoIn(e.getOvertimeAutoIn());
			atempDailyDb.setOvertimeAutoOut(e.getOvertimeAutoOut());
			atempDailyDb.setOvertimeBreakEnd(e.getOvertimeBreakEnd());
			atempDailyDb.setOvertimeBreakStart(e.getOvertimeBreakStart());
			atempDailyDb.setOvertimeFactorCode(e.getOvertimeFactorCode());
			atempDailyDb.setOvertimeOutTime(e.getOvertimeOutTime());
			atempDailyDb.setPLCPT(e.getPLCPT());
			atempDailyDb.setProcessFlag(e.getProcessFlag());
			atempDailyDb.setPTSWP(e.getPTSWP());
			atempDailyDb.setQuickOutTime(e.getQuickOutTime());
			atempDailyDb.setReason(e.getReason());
			atempDailyDb.setEditReason(e.getEditReason());
			atempDailyDb.setEditRemark(e.getEditRemark());
			atempDailyDb.setRemark(e.getRemark());
			atempDailyDb.setRequestOvertimeIn(e.getRequestOvertimeIn());
			atempDailyDb.setRequestOvertimeOut(e.getRequestOvertimeOut());
			atempDailyDb.setShiftBreakEnd(e.getOvertimeBreakEnd());
			atempDailyDb.setOvertimeBreakStart(e.getOvertimeBreakStart());
			atempDailyDb.setShiftCode(e.getShiftCode());
			atempDailyDb.setShiftOvertimeEnd(e.getShiftOvertimeEnd());
			atempDailyDb.setShiftOvertimeStart(e.getShiftOvertimeStart());
			atempDailyDb.setStartOvertimeLimit(e.getStartOvertimeLimit());
			atempDailyDb.setStartRangeIn(e.getStartRangeIn());
			atempDailyDb.setStartRangeOut(e.getStartRangeOut());
			atempDailyDb.setTLHDR(e.getTLHDR());
			atempDailyDb.setTotalOvertimeDay(e.getTotalOvertimeDay());
			atempDailyDb.setTotalOvertimeIndex(e.getTotalOvertimeIndex());
			atempDailyDb.setTotalOvertimeIndexFormula(e.getTotalOvertimeIndexFormula());
			atempDailyDb.setTransactionCode(e.getTransactionCode());
			atempDailyDb.setTransportOvertime(e.getTransportOvertime());
			atempDailyDb.setTransportOvertimeHd(e.getTransportOvertimeHd());
			atempDailyDb.setTransactionRefNo(e.getTransactionRefNo());
			atempDailyDb.setWorkDate(e.getWorkDate());
			atempDailyDb.setCompany(this.companyid);
			atempDailyService.save(atempDailyDb);
			System.out.println("Success Save AtEmpDaily");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<AtempDaily> listAtempDaily = atempDailyService.findNeedSync();
		if(listAtempDaily != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(AtempDaily atempDaily : listAtempDaily){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Ot_Accepted_In__c", atempDaily.getAcceptedOvertimeIn());
				map.put("Ot_Accepted_Out__c", atempDaily.getAcceptedOvertimeOut());
				map.put("Accepted_Overtime_Out_Fromday__c", atempDaily.getAcceptedOvertimeOutFromDay());
				map.put("Actual_In_Time__c", atempDaily.getActualInTime());
				map.put("Actual_In_Time_GMT__c", atempDaily.getActualInTimeGmt());
				map.put("Actual_Out_Time__c", atempDaily.getActualOutTime());
				map.put("Card_No__c", atempDaily.getCardNo());
				map.put("Compare_Field__c", atempDaily.getCompareField());
				map.put("Day_Type__c", atempDaily.getDayType());
				map.put("Edit_In_Time__c", atempDaily.getEditInTime());
				map.put("Edit_In_Time_LPAD__c", atempDaily.getEditInTimeLpad());
				map.put("Edit_Out_Time__c", atempDaily.getEditOutTime());
				map.put("Edit_Out_Time_LPAD__c", atempDaily.getEditOutTimeLpad());
				map.put("Employee_No__c", atempDaily.getEmployeeNo());
				map.put("End_Ovt_Limit__c", atempDaily.getEndOvertimeLimit());
				map.put("End_Range_In__c", atempDaily.getEndRangeIn());
				map.put("End_Range_Out__c", atempDaily.getEndRangeOut());
				map.put("OT_IndexOT_In__c", atempDaily.getIndexOvertimeIn());
				map.put("Ovt_Index_Out_Formula__c", atempDaily.getIndexOvertimeOut());
				map.put("OT_IndexOT_Out_1__c", atempDaily.getIndexOvertimeOut1());
				map.put("OT_IndexOT_Out_2__c", atempDaily.getIndexOvertimeOut2());
				map.put("OT_IndexOT_Out_3__c", atempDaily.getIndexOvertimeOut3());
				map.put("OT_IndexOT_Out_4__c", atempDaily.getIndexOvertimeOut4());
				map.put("Index_Overtime_Out_Fromday__c", atempDaily.getIndexOvertimeOutFromday());
				map.put("Index_Overtime_Out_Fromday_Formula__c", atempDaily.getIndexOvertimeOutFromdayFormula());
				map.put("In_Time__c", atempDaily.getInTime());
				map.put("In_Time_Ext__c", atempDaily.getInTimeExtra());
				map.put("In_Time_Extra_Formula__c", atempDaily.getInTimeExtraFormula());
				map.put("Late_In_Time__c", atempDaily.getLateInTime());
				map.put("Location_In__c", atempDaily.getLocationIn());
				map.put("Location_Out__c", atempDaily.getLocationOut());
				map.put("Machine_No__c", atempDaily.getMachineNo());
				map.put("Machine_Terminal__c", atempDaily.getMachineTerminal());
				map.put("Meal_Ot2__c", atempDaily.getMealOvertime());
				map.put("MealOt_HD2__c", atempDaily.getMealOvertimeHd());
				map.put("MTSWP__c", atempDaily.getMTSWP());
				map.put("Out_End_Time_Min_Date__c", atempDaily.getOutEndTimeMin());
				map.put("Out_End_Time_Max_Date__c", atempDaily.getOutEndTimeMax());
				map.put("Out_Time__c", atempDaily.getOutTime());
				map.put("Out_Time_Ext__c", atempDaily.getOutTimeExtra());
				map.put("Out_Time_Extra_Formula__c", atempDaily.getOutTimeExtraFormula());
				map.put("Out_Time_Extra_Ovt__c", atempDaily.getOutTimeExtraOvertime());
				map.put("Ovt_Auto_In__c", atempDaily.getOvertimeAutoIn());
				map.put("Overtime_Auto_Out__c", atempDaily.getOvertimeAutoOut());
				map.put("Overtime_Break_End__c", atempDaily.getOvertimeBreakEnd());
				map.put("Overtime_Break_Start__c", atempDaily.getOvertimeBreakStart());
				map.put("Ot_Factor_Code__c", atempDaily.getOvertimeFactorCode());
				map.put("Overtime_Out_Time__c", atempDaily.getOvertimeOutTime());
				map.put("PLCPT__c", atempDaily.getPLCPT());
				map.put("Process_Flag__c", atempDaily.getProcessFlag());
				map.put("PTSWP__c", atempDaily.getPTSWP());
				map.put("Quick_Out_Time__c", atempDaily.getQuickOutTime());
				map.put("Reason__c", atempDaily.getReason());
				map.put("Remark__c", atempDaily.getRemark());
				map.put("Req_Ot_In__c", atempDaily.getRequestOvertimeIn());
				map.put("Req_Ot_Out__c", atempDaily.getRequestOvertimeOut());
				map.put("Shift_Break_End__c", atempDaily.getShiftBreakEnd());
				map.put("Shift_Break_Start__c", atempDaily.getShiftBreakStart());
				map.put("Shift_Code__c", atempDaily.getShiftCode());
				map.put("Shift_Overtime_End__c", atempDaily.getShiftOvertimeEnd());
				map.put("Shift_Overtime_Start__c", atempDaily.getShiftOvertimeStart());
				map.put("Start_Ovt_Limit__c", atempDaily.getStartOvertimeLimit());
				map.put("Start_Range_In__c", atempDaily.getStartRangeIn());
				map.put("Start_Range_Out__c", atempDaily.getStartRangeOut());
				map.put("TLHDR__c", atempDaily.getTLHDR());
				map.put("Ot_Total_Day__c", atempDaily.getTotalOvertimeDay());
				map.put("Total_Overtime_Index__c", atempDaily.getTotalOvertimeIndex());
				map.put("Total_Ovt_Index__c", atempDaily.getTotalOvertimeIndexFormula());
				map.put("Attend_Code__c", atempDaily.getTransactionCode());
				map.put("Transport_Ot2__c", atempDaily.getTransportOvertime());
				map.put("Transport_Ot_HD2__c", atempDaily.getTransportOvertimeHd());
				map.put("Request_Tran_RefNo__c", atempDaily.getTransactionRefNo());
				map.put("Work_Date__c", atempDaily.getWorkDate());
				listMap.add(map);
				System.out.println("No " + (i+1) + " : " + atempDaily.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + "Task Already Sending");
		}
	}
	
	@Override
	public void sendDataAckSync() {
		List<Object[]> listDataAckSync = atempDailyRepository.findSendAckSync();
		sendForceDataAckSync(listDataAckSync);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, String extId) {
		atempDailyService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		atempDailyService.updateAckSyncStatus(status, extId);
	}
	
	@Override
	public void initRetrieve(){
//		System.out.println("Masuk 1");
		
		SyncSettings syncSettings = syncSettingsRepository.findByModuleName(this.forceModuleName);
		Date today = new Date();
		Date syncFromDate = syncSettings.getLastSyncDate();
		Date syncToDate = null;
		String strsyncToDate = "";
		try {
			syncToDate = Utils.addDay(syncFromDate, syncSettings.getSyncDays());
			strsyncToDate = Utils.convertDateToString(syncToDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map<String,Object> mapLoginResult = loginForce();
		if(mapLoginResult != null) {
			accessToken = (String) mapLoginResult.get("access_token");
			instanceUrl = (String) mapLoginResult.get("instance_url");
			tokenType = (String) mapLoginResult.get("token_type");
			
			// get count of row
			String urlQueryGetCount = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncCount=true&SyncExtId=null&SyncFromDate="+syncFromDate+"&SyncToDate="+strsyncToDate;
			System.out.println("request URL : " + urlQueryGetCount);
			MultiValueMap<String, String> multiValueHeaders = new LinkedMultiValueMap<String, String>();
			String strAuthorization = tokenType + " " + accessToken;
			multiValueHeaders.add("Authorization", strAuthorization);
			HttpEntity<Object> entityQuery = new HttpEntity<Object>(
					multiValueHeaders);
			ResponseEntity<String> resultQuery = restTemplate.exchange(urlQueryGetCount,
					HttpMethod.GET, entityQuery, String.class);
			try {
				ForceResponse forceResponse = (ForceResponse) objectMapper
						.readValue(resultQuery.getBody(), ForceResponse.class);
				if (forceResponse != null) {
					
					int total = getTotal(forceResponse.getItems());
					int loop = (total/TOTAL_ROW_PER_REQUEST )+ 1;
//					int loop =1;
					for(int i =0; i<loop ;i++){
						String queryGetData = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncExtId=null&SyncStart=0&SyncLimit="+TOTAL_ROW_PER_REQUEST+"&SyncFromDate="+syncFromDate+"&SyncToDate="+strsyncToDate;
//						String queryGetData = instanceUrl + "/services/apexrest/GetAll?SyncObject=" + this.forceModuleName+"&SyncExtId=null&SyncStart=2998&SyncLimit="+TOTAL_ROW_PER_REQUEST;
						
						receive(queryGetData,true);
						sendDataAckSync();
						
					}
					
					
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				syncSettings.setLastSyncDate(Utils.addDay(today, -1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
		}
	}
	
}
