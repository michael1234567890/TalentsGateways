package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.EmployeePayroll;
import com.phincon.talents.gateways.services.EmployeePayrollService;

@Service
public class EmployeePayrollForceAdapeter extends ForceAdapter<EmployeePayroll>{
	
	@Autowired
	EmployeePayrollService employeePayrollService;
	
	public EmployeePayrollForceAdapeter(){
		super();
		query = "PYEMPPAYROLL__c";
	}
	
	@Override
	protected EmployeePayroll convertMapResultToObject(Map<String, Object> mapResult){
		
		String extId = "";
		if(mapResult.get("Id") != null)
			extId = (String) mapResult.get("Id");
		else
			extId = null;
		
		String accountName = "";
		if(mapResult.get("Account_Name__c") != null)
			accountName = (String) mapResult.get("Account_Name__c");
		else
			accountName = null;
		
		String attendanceGroupCode = "";
		if(mapResult.get("Attendance_Group_Code__c") != null)
			attendanceGroupCode = (String) mapResult.get("Attendance_Group_Code__c");
		else
			attendanceGroupCode = null;
		
		String bankAccount = "";
		if(mapResult.get("Bank_Account__c") != null)
			bankAccount = (String) mapResult.get("Bank_Account__c");
		else
			bankAccount = null;
		
		String bankBranch = "";
		if(mapResult.get("Bank_Branch__c") != null)
			bankBranch = (String) mapResult.get("Bank_Branch__c");
		else
			bankBranch = null;
		
		String bankLocation = "";
		if(mapResult.get("Bank_Location__c") != null)
			bankLocation = (String) mapResult.get("Bank_Location__c");
		else
			bankLocation = null;
		
		String bankName = "";
		if(mapResult.get("Bank_Name__c") != null)
			bankName = (String) mapResult.get("Bank_Name__c");
		else
			bankName = null;
		
		String bankTransferType = "";
		if(mapResult.get("Bank_Transfer_Type__c") != null)
			bankTransferType = (String) mapResult.get("Bank_Transfer_Type__c");
		else
			bankTransferType = null;
		
		String companyName = "";
		if(mapResult.get("Company_Name__c") != null)
			companyName = (String) mapResult.get("Company_Name__c");
		else
			companyName = null;
		
		String employeeNo = "";
		if(mapResult.get("Employee_No__c") != null)
			employeeNo = (String) mapResult.get("Employee_No__c");
		else
			employeeNo = null;
		
		Boolean flagCOP = null;
		if(mapResult.get("Flag_COP__c") != null)
			flagCOP = (Boolean) mapResult.get("Flag_COP__c");
		
		Boolean flagIncentive = null;
		if(mapResult.get("Flag_Incentive__c") != null)
			flagIncentive = (Boolean) mapResult.get("Flag_Incentive__c");
		
		Boolean flagKost = null;
		if(mapResult.get("Flag_Kost__c") != null)
			flagKost = (Boolean) mapResult.get("Flag_Kost__c");
		
		Boolean flagLembur = null;
		if(mapResult.get("Flag_Lembur__c") != null)
			flagLembur = (Boolean) mapResult.get("Flag_Lembur__c");
		
		Boolean flagOperasional = null;
		if(mapResult.get("Flag_Operational__c") != null)
			flagOperasional = (Boolean) mapResult.get("Flag_Operational__c");
		
		Boolean isDeleted = null;
		if(mapResult.get("IsDeleted") != null)
			isDeleted = (Boolean) mapResult.get("IsDeleted");
		
		Boolean isProcessedPrevious = null;
		if(mapResult.get("Is_Processed_Previous__c") != null)
			isProcessedPrevious = null;
		
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
		
		String payType = "";
		if(mapResult.get("Pay_Type__c") != null)
			payType = (String) mapResult.get("Pay_Type__c");
		else
			payType = null;
		
		String paymentMethod = "";
		if(mapResult.get("Payment_Method__c") != null)
			paymentMethod = (String) mapResult.get("Payment_Method__c");
		else
			paymentMethod = null;
		
		Double previousBJBT = null;
		if(mapResult.get("Previous_BJBT__c") != null)
			previousBJBT = (Double) mapResult.get("Previous_BJBT__c");
		
		Double previousNetIncome = null;
		if(mapResult.get("Previous_Net_Income__c") != null)
			previousNetIncome = (Double) mapResult.get("Previous_Net_Income__c");
		
		Double previousOtherPTKP = null;
		if(mapResult.get("Previous_Other_PTKP__c") != null)
			previousOtherPTKP = (Double) mapResult.get("Previous_Other_PTKP__c");
		
		Double previousPTKP = null;
		if(mapResult.get("Previous_PTKP__c") != null)
			previousPTKP = (Double) mapResult.get("Previous_PTKP__c");
		
		Integer previousPeriod = null;
		if(mapResult.get("Previous_Period__c") != null)
			previousPeriod = (Integer) mapResult.get("Previous_Period__c");
		
		Double previousTaxPenalty = null;
		if(mapResult.get("Previous_Tax_Penalty__c") != null)
			previousTaxPenalty = (Double) mapResult.get("Previous_Tax_Penalty__c");
		
		Double previousTax = null;
		if(mapResult.get("Previous_Tax__c") != null)
			previousTax = (Double) mapResult.get("Previous_Tax__c");
		
		String taxFileNoName = "";
		if(mapResult.get("Tax_File_No_Name__c") != null)
			taxFileNoName = (String) mapResult.get("Tax_File_No_Name__c");
		else
			taxFileNoName = null;
		
		String taxFileNo = "";
		if(mapResult.get("Tax_File_No__c") != null)
			taxFileNo = (String) mapResult.get("Tax_File_No__c");
		else
			taxFileNo = null;
		
		String taxLocationName = "";
		if(mapResult.get("Tax_Location_Name__c") != null)
			taxLocationName = (String) mapResult.get("Tax_Location_Name__c");
		else
			taxLocationName = null;
		
		String taxStatus = "";
		if(mapResult.get("Tax_Status__c") != null)
			taxStatus = (String) mapResult.get("Tax_Status__c");
		else
			taxStatus = null;
		
		String taxType = "";
		if(mapResult.get("Tax_Type__c") != null)
			taxType = (String) mapResult.get("Tax_Type__c");
		
		Boolean isReguler = null;
		if(mapResult.get("isReguler__c") != null)
			isReguler = (Boolean) mapResult.get("isReguler__c");
		
		EmployeePayroll empPayroll = new EmployeePayroll();
		empPayroll.setExtId(extId);
		empPayroll.setAccountName(accountName);
		empPayroll.setAttendanceGroupCode(attendanceGroupCode);
		empPayroll.setBankAccount(bankAccount);
		empPayroll.setBankBranch(bankBranch);
		empPayroll.setBankLocation(bankLocation);
		empPayroll.setBankName(bankName);
		empPayroll.setBankTransferType(bankTransferType);
		empPayroll.setBankName(bankName);
		empPayroll.setBankTransferType(bankTransferType);
		empPayroll.setCompanyName(companyName);
		empPayroll.setEmployeeNo(employeeNo);
		empPayroll.setFlagCOP(flagCOP);
		empPayroll.setFlagIncentive(flagIncentive);
		empPayroll.setFlagKost(flagKost);
		empPayroll.setFlagLembur(flagLembur);
		empPayroll.setFlagOperasional(flagOperasional);
		empPayroll.setIsDeleted(isDeleted);
		empPayroll.setIsProcessedPrevious(isProcessedPrevious);
		empPayroll.setName(name);
		empPayroll.setOwnerId(ownerId);
		empPayroll.setPayType(payType);
		empPayroll.setPaymentMethod(paymentMethod);
		empPayroll.setPreviousBJBT(previousBJBT);
		empPayroll.setPreviousNetIncome(previousNetIncome);
		empPayroll.setPreviousOtherPTKP(previousOtherPTKP);
		empPayroll.setPreviousPTKP(previousPTKP);
		empPayroll.setPreviousPeriod(previousPeriod);
		empPayroll.setPreviousTaxPenalty(previousTaxPenalty);
		empPayroll.setPreviousTax(previousTax);
		empPayroll.setTaxFileNoName(taxFileNoName);
		empPayroll.setTaxFileNo(taxFileNo);
		empPayroll.setTaxLocationName(taxLocationName);
		empPayroll.setTaxStatus(taxStatus);
		empPayroll.setTaxType(taxType);
		empPayroll.setIsReguler(isReguler);
		
		return empPayroll;
	}
	
	@Override
	public void saveListData(List<EmployeePayroll> listData, boolean isAckSend){
		for(EmployeePayroll e : listData){
			System.out.print("Employee Payroll : " +e.getExtId());
			// check is id exist
			EmployeePayroll empPayroll = employeePayrollService.findByExtId(e.getExtId());
			// if exist doing update
			if(empPayroll == null){
				empPayroll = new EmployeePayroll();
				empPayroll.setCreatedDate(new Date());
			}
			empPayroll.setExtId(e.getExtId());
			empPayroll.setCompany(this.companyid);
			empPayroll.setAccountName(e.getAccountName());
			empPayroll.setAttendanceGroupCode(e.getAttendanceGroupCode());
			empPayroll.setBankAccount(e.getBankAccount());
			empPayroll.setBankBranch(e.getBankBranch());
			empPayroll.setBankLocation(e.getBankLocation());
			empPayroll.setBankName(e.getBankName());
			empPayroll.setBankTransferType(e.getBankTransferType());
			empPayroll.setCompanyName(e.getCompanyName());
			empPayroll.setEmployeeNo(e.getEmployeeNo());
			empPayroll.setFlagCOP(e.getFlagCOP());
			empPayroll.setFlagIncentive(e.getFlagIncentive());
			empPayroll.setFlagKost(e.getFlagKost());
			empPayroll.setFlagLembur(e.getFlagLembur());
			empPayroll.setFlagOperasional(e.getFlagOperasional());
			empPayroll.setIsDeleted(e.getIsDeleted());
			empPayroll.setIsProcessedPrevious(e.getIsProcessedPrevious());
			empPayroll.setName(e.getName());
			empPayroll.setOwnerId(e.getOwnerId());
			empPayroll.setPayType(e.getPayType());
			empPayroll.setPaymentMethod(e.getPaymentMethod());
			empPayroll.setPreviousBJBT(e.getPreviousBJBT());
			empPayroll.setPreviousNetIncome(e.getPreviousNetIncome());
			empPayroll.setPreviousOtherPTKP(e.getPreviousOtherPTKP());
			empPayroll.setPreviousPTKP(e.getPreviousPTKP());
			empPayroll.setPreviousPeriod(e.getPreviousPeriod());
			empPayroll.setPreviousTax(e.getPreviousTax());
			empPayroll.setPreviousTaxPenalty(e.getPreviousTaxPenalty());
			empPayroll.setTaxFileNo(e.getTaxFileNo());
			empPayroll.setTaxFileNoName(e.getTaxFileNoName());
			empPayroll.setTaxLocationName(e.getTaxLocationName());
			empPayroll.setTaxStatus(e.getTaxStatus());
			empPayroll.setTaxType(e.getTaxType());
			empPayroll.setIsReguler(e.getIsReguler());
			
			employeePayrollService.save(empPayroll);
			System.out.println("Success Save PyEmpPayroll");
		}
	}
	
	@Override
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<EmployeePayroll> listEmpPayroll = employeePayrollService.findNeedSync();
		if(listEmpPayroll != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(EmployeePayroll empPayroll : listEmpPayroll){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Account_Name__c", empPayroll.getAccountName());
				map.put("Attendance_Group_Code__c", empPayroll.getAttendanceGroupCode());
				map.put("Bank_Account__c", empPayroll.getBankAccount());
				map.put("Bank_Branch__c", empPayroll.getBankBranch());
				map.put("Bank_Location__c", empPayroll.getBankLocation());
				map.put("Bank_Name__c", empPayroll.getBankName());
				map.put("Bank_Transfer_Type__c", empPayroll.getBankTransferType());
				map.put("Company_Name__c", empPayroll.getCompanyName());
				map.put("Employee_No__c", empPayroll.getEmployeeNo());
				map.put("Flag_COP__c", empPayroll.getFlagCOP());
				map.put("Flag_Incentive__c", empPayroll.getFlagIncentive());
				map.put("Flag_Kost__c", empPayroll.getFlagKost());
				map.put("Flag_Lembur__c", empPayroll.getFlagLembur());
				map.put("Flag_Operational__c", empPayroll.getFlagOperasional());
				map.put("IsDeleted", empPayroll.getIsDeleted());
				map.put("Is_Processed_Previous__c", empPayroll.getIsProcessedPrevious());
				map.put("Name", empPayroll.getName());
				map.put("OwnerId", empPayroll.getOwnerId());
				map.put("Pay_Type__c", empPayroll.getPayType());
				map.put("Payment_Method__c", empPayroll.getPaymentMethod());
				map.put("Previous_BJBT__c", empPayroll.getPreviousBJBT());
				map.put("Previous_Net_Income__c", empPayroll.getPreviousNetIncome());
				map.put("Previous_Other_PTKP__c", empPayroll.getPreviousOtherPTKP());
				map.put("Previous_PTKP__c", empPayroll.getPreviousPTKP());
				map.put("Previous_Period__c", empPayroll.getPreviousPeriod());
				map.put("Previous_Tax_Penalty__c", empPayroll.getPreviousTaxPenalty());
				map.put("Previous_Tax__c", empPayroll.getPreviousTax());
				map.put("Tax_File_No_Name__c", empPayroll.getTaxFileNoName());
				map.put("Tax_File_No__c", empPayroll.getTaxFileNo());
				map.put("Tax_Location_Name__c", empPayroll.getTaxLocationName());
				map.put("Tax_Status__c", empPayroll.getTaxStatus());
				map.put("Tax_Type__c", empPayroll.getTaxType());
				map.put("isReguler__c", empPayroll.getIsReguler());
				listMap.add(map);
				System.out.println("No " + (i+1) + " : " + empPayroll.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap,false);
			System.out.println(i + "Task Already Sending");
		}
	}
}
