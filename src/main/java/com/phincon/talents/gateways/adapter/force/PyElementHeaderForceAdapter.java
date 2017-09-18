package com.phincon.talents.gateways.adapter.force;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Assignment;
import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.model.PayrollElementHeader;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.PayrollElementHeaderService;

@Service
public class PyElementHeaderForceAdapter extends ForceAdapter<PayrollElementHeader> {

	@Autowired
	PayrollElementHeaderService payrollElementHeaderService;
	

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	
	public PyElementHeaderForceAdapter() {
		super();
	}
	
	@Override
	protected PayrollElementHeader convertMapResultToObject(Map<String, Object> mapResult){
		Double baseSalary = (Double) mapResult.get("Base_Salary__c");
		Double ptkp = (Double) mapResult.get("PTKP__c");
		Double pkp = (Double) mapResult.get("PKP__c");  
		Double otherPtkpYearly = (Double) mapResult.get("Other_PTKP_Yearly__c");  
		Double otherPtkp = (Double) mapResult.get("Other_PTKP__c");  
		
		Boolean isSeverencePay = (Boolean) mapResult.get("Is_Severence_Pay__c");
		Integer terminateProcess = (Integer) mapResult.get("Terminate_Process__c");  
		Double maximalBJBT = (Double) mapResult.get("Maximal_BJBT__c");  
		Double irregGrossIncomeYearly = (Double) mapResult.get("IrregGross_Income_Yearly__c");   
		Double irregNetIncomeYearly = (Double) mapResult.get("IrregNet_Income_Yearly__c");   
		Double netBjbt= (Double) mapResult.get("Nett_BJBT__c");  
		Double netIncomeYearly = (Double) mapResult.get("Net_Income_Yearly__c");  
		String name =(String) mapResult.get("Name");   
		Double grossBJBT = (Double) mapResult.get("Gross_BJBT__c");  
		Double prevIrregGrossIncome = (Double) mapResult.get("Prev_IrregGross_Income__c");  
		Double prevIrregNetIncome = (Double) mapResult.get("Prev_IrregNet_Income__c");  
		
		String remark = (String) mapResult.get("Remark__c");  
		Double sumNonTaxIncome = (Double) mapResult.get("Sum_NonTax_Income__c");  
		Double takeHomePay = (Double) mapResult.get("Take_Home_Pay__c"); 
		Double taxGross =  (Double) mapResult.get("Tax_Gross__c");
		Double taxAllowance =  (Double) mapResult.get("Tax_Allowance__c");
		
		Double taxPenalty=  (Double) mapResult.get("Tax_Penalty__c");
		String taxStatus =  (String) mapResult.get("Tax_Status__c");
		String extId = (String) mapResult.get("Id");
		System.out.println("Ext Id " + extId);
		String taxType = (String) mapResult.get("Tax_Type__c");
		Double totalAllIncomeMonthly = (Double) mapResult.get("Total_All_Income_Monthly__c");
		Double totalAllowance = (Double) mapResult.get("Total_Allowance__c");
		Double valueResultTypeTax = (Double) mapResult.get("Value_Result_Type_Tax__c");
		Double totalDeduction = (Double) mapResult.get("Total_Deduction__c"); 
		Double totalIncome = (Double) mapResult.get("Total_Income__c"); 
		String employeeExtId = (String) mapResult.get("Employee_Name__c"); 
		String employmentExtId = (String) mapResult.get("Employee_No__c"); 
		String periodDate = (String) mapResult.get("Payment_Start_Date__c");  
		Double regNetIncome = (Double) mapResult.get("RegNet_Income__c");  
		
		Double prevRegGrossIncome = (Double) mapResult.get("Prev_RegGross_Income__c");  
		Double prevRegNetIncome = (Double) mapResult.get("Prev_RegNet_Income__c");  
		Double regGrossIncome = (Double) mapResult.get("RegGross_Income__c");  
		Double taxPenaltyPercentage =  (Double) mapResult.get("Tax_Penalty_Percentage__c");
		Double regNetIncomeyearly = (Double) mapResult.get("RegNet_Income_Yearly__c");  
		Double regGrossIncomeyearly = (Double) mapResult.get("RegGross_Income_Yearly__c");  
		String assignmentExtId = (String) mapResult.get("Assignment_No__c");  
		String bankAccount = (String) mapResult.get("Bank_Account__c");  
		String bankBranch = (String) mapResult.get("Bank_Branch__c");  
		String accountName = (String) mapResult.get("Account_Name__c");  
		
		
		PayrollElementHeader obj = new PayrollElementHeader();
		obj.setBaseSalary(baseSalary);
		obj.setBankAccount(bankAccount);
		obj.setBankBranch(bankBranch);
		obj.setAccountName(accountName);
		obj.setPtkp(ptkp);
		obj.setOtherPtkp(otherPtkp);
		obj.setOtherPtkpYearly(otherPtkpYearly);
		obj.setPkp(pkp);
		obj.setAssignmentExtId(assignmentExtId);
		obj.setEmployeeExtId(employeeExtId);
		obj.setEmploymentExtId(employmentExtId);
		obj.setExtId(extId);
		
		
//		obj.setAccountName(accountName);
//		obj.setBankAccount(bankAccount);
//		obj.setBankName(bankName);
		
		
		
		obj.setGrossBJBT(grossBJBT);
		obj.setIrregGrossIncomeYearly(irregGrossIncomeYearly);
		obj.setIrregNetIncomeYearly(irregNetIncomeYearly);
		obj.setIsSeverencePay(isSeverencePay);
		obj.setMaximalBJBT(maximalBJBT);
		obj.setPrevRegGrossIncome(prevIrregGrossIncome);
		obj.setPrevRegNetIncome(prevRegNetIncome);
		obj.setPrevRegGrossIncome(prevRegGrossIncome);
		obj.setRegGrossIncome(regGrossIncome);
		obj.setTaxPenaltyPercentage(taxPenaltyPercentage);
		obj.setRegNetIncomeYearly(regNetIncomeyearly);
		obj.setRegGrossIncomeYearly(regGrossIncomeyearly);
		obj.setName(name);
		obj.setNetBjbt(netBjbt);
		obj.setNetIncomeYearly(netIncomeYearly);
		obj.setPeriodDate(periodDate);
		obj.setPrevIrregGrossIncome(prevIrregGrossIncome);
		obj.setPrevIrregNetIncome(prevIrregNetIncome);
		obj.setRegNetIncome(regNetIncome);
		obj.setRemark(remark);
		obj.setSumNonTaxIncome(sumNonTaxIncome);
		obj.setTakeHomePay(takeHomePay);
		obj.setTaxAllowance(taxAllowance);
		obj.setTaxGross(taxGross);
		obj.setTaxPenalty(taxPenalty);
		obj.setTaxStatus(taxStatus);
		obj.setTaxType(taxType);
		obj.setTerminateProcess(terminateProcess);
		obj.setTotalAllIncomeMonthly(totalAllIncomeMonthly);
		obj.setTotalAllowance(totalAllowance);
		obj.setTotalDeduction(totalDeduction);
		obj.setTotalIncome(totalIncome);
		obj.setValueResultTypeTax(valueResultTypeTax);
		
		return obj;
	}
	
	@Override
	public void saveListData(List<PayrollElementHeader> listData, boolean isInit){
		for(PayrollElementHeader e : listData){
			System.out.println("After Ext Id " + e.getExtId());
			PayrollElementHeader payrollElementHeader = payrollElementHeaderService.findByExtId(e.getExtId());
			
			if(payrollElementHeader == null){
				payrollElementHeader = new PayrollElementHeader() ;
				payrollElementHeader.setCreatedDate(new Date());
				payrollElementHeader.setCreatedBy("Talents Gateway");
				payrollElementHeader.setExtId(e.getExtId());
			}
			
			if(isInit)
				payrollElementHeader.setAckSync(false);
			payrollElementHeader.setBankAccount(e.getBankAccount());
			payrollElementHeader.setBankBranch(e.getBankBranch());
			payrollElementHeader.setAccountName(e.getAccountName());
			payrollElementHeader.setPkp(e.getPkp());
			payrollElementHeader.setPtkp(e.getPtkp());
			payrollElementHeader.setOtherPtkp(e.getOtherPtkp());
			payrollElementHeader.setOtherPtkpYearly(e.getOtherPtkpYearly());
			payrollElementHeader.setGrossBJBT(e.getGrossBJBT());
			payrollElementHeader.setIrregGrossIncomeYearly(e.getIrregGrossIncomeYearly());
			payrollElementHeader.setIrregNetIncomeYearly(e.getIrregNetIncomeYearly());
			payrollElementHeader.setIsSeverencePay(e.getIsSeverencePay());
			payrollElementHeader.setMaximalBJBT(e.getMaximalBJBT());
			payrollElementHeader.setPrevRegGrossIncome(e.getPrevRegGrossIncome());
			payrollElementHeader.setPrevRegNetIncome(e.getPrevRegNetIncome());
			payrollElementHeader.setPrevRegGrossIncome(e.getPrevRegGrossIncome());
			payrollElementHeader.setRegGrossIncome(e.getRegGrossIncome());
			payrollElementHeader.setTaxPenaltyPercentage(e.getTaxPenaltyPercentage());
			payrollElementHeader.setRegNetIncomeYearly(e.getRegNetIncomeYearly());
			payrollElementHeader.setRegGrossIncomeYearly(e.getRegGrossIncomeYearly());
			payrollElementHeader.setName(e.getName());
			payrollElementHeader.setNetBjbt(e.getNetBjbt());
			payrollElementHeader.setNetIncomeYearly(e.getNetIncomeYearly());
			payrollElementHeader.setPeriodDate(e.getPeriodDate());
			payrollElementHeader.setPrevIrregGrossIncome(e.getPrevIrregGrossIncome());
			payrollElementHeader.setPrevIrregNetIncome(e.getPrevIrregNetIncome());
			payrollElementHeader.setRegNetIncome(e.getRegNetIncome());
			payrollElementHeader.setRemark(e.getRemark());
			payrollElementHeader.setSumNonTaxIncome(e.getSumNonTaxIncome());
			payrollElementHeader.setTakeHomePay(e.getTakeHomePay());
			payrollElementHeader.setTaxAllowance(e.getTaxAllowance());
			payrollElementHeader.setTaxGross(e.getTaxGross());
			payrollElementHeader.setTaxPenalty(e.getTaxPenalty());
			payrollElementHeader.setTaxStatus(e.getTaxStatus());
			payrollElementHeader.setTaxType(e.getTaxType());
			payrollElementHeader.setTerminateProcess(e.getTerminateProcess());
			payrollElementHeader.setTotalAllIncomeMonthly(e.getTotalAllIncomeMonthly());
			payrollElementHeader.setTotalAllowance(e.getTotalAllowance());
			payrollElementHeader.setTotalDeduction(e.getTotalDeduction());
			payrollElementHeader.setTotalIncome(e.getTotalIncome());
			payrollElementHeader.setValueResultTypeTax(e.getValueResultTypeTax());
			payrollElementHeader.setBaseSalary(e.getBaseSalary());
			
			Employee employee = null ;
			if(e.getEmployeeExtId() != null)
				employee = employeeService.findByExtId(e.getEmployeeExtId());
			
			Employment employment = null;
			if(e.getEmploymentExtId() != null)
				employment = employmentService.findByExtId(e.getEmploymentExtId());
			
			
			Assignment assignment = null ;
			if(e.getAssignmentExtId() != null)
				assignment = assignmentService.findByExtId(e.getAssignmentExtId());

			payrollElementHeader.setEmployee(employee);
			payrollElementHeader.setEmployment(employment);
			payrollElementHeader.setAssignment(assignment);
			
			payrollElementHeader.setCompany(this.companyid);
			payrollElementHeader.setModifiedDate(new Date());
			payrollElementHeader.setModifiedBy("Talents Gateway");
			payrollElementHeaderService.save(payrollElementHeader);
			
			System.out.println("PayrollElementHeader Save");
		}
	}
	
}
