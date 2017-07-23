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
import com.phincon.talents.gateways.model.PayrollElementHeaderYearly;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.PayrollElementHeaderYearlyService;

@Service
public class PyElementHeaderYearlyForceAdapter extends ForceAdapter<PayrollElementHeaderYearly> {

	@Autowired
	PayrollElementHeaderYearlyService payrollElementHeaderYearlyService;
	

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	
	public PyElementHeaderYearlyForceAdapter() {
		super();
	}
	
	@Override
	protected PayrollElementHeaderYearly convertMapResultToObject(Map<String, Object> mapResult){
		
		
		// Double taxPaid = (Double) mapResult.get("Tax_Paid__c");
		String taxStatus = (String) mapResult.get("Tax_Status__c");

		String extId = (String) mapResult.get("Id");
		String taxType = (String) mapResult.get("Tax_Type__c");
		String employmentExtId = (String) mapResult.get("Employee_No__c");
		Double pph21Terhutang = (Double) mapResult.get("PPH21_Terhutang__c");
		Double pph21DipotongDilunasi = (Double) mapResult.get("PPH21_telah_dipotong_dan_dilunasi__c");
		String currentYear = (String) mapResult.get("Current_Year__c");
		Double jumlahPhBruto = (Double) mapResult.get("Jumlah_Ph_Bruto__c");
//		Double totalDeduction = (Double) mapResult.get("Jumlah_Pengurangan__c");
		Double totalDeduction = (Double) mapResult.get("Total_Deduction__c");
		Double totalAllowance = (Double) mapResult.get("Total_Allowance__c");
		Double currentTaxAllowance = (Double) mapResult.get("Current_Tax_Allowance__c");  
		Double currentTaxGross = (Double) mapResult.get("Current_Tax_Gross__c");
		Double currentTaxPenalty = (Double) mapResult.get("Current_Tax_Penalty__c");
		
		PayrollElementHeaderYearly obj = new PayrollElementHeaderYearly();
		obj.setExtId(extId);
		obj.setCurrentYear(currentYear);
		//obj.setTaxPaid(taxPaid);
		obj.setTaxStatus(taxStatus);
		obj.setTaxType(taxType);
		obj.setEmploymentExtId(employmentExtId);
		obj.setPph21DipotongDilunasi(pph21DipotongDilunasi);
		obj.setPph21Terhutang(pph21Terhutang);
		obj.setCurrentYear(currentYear);
		obj.setJumlahPhBruto(jumlahPhBruto);
		obj.setTotalDeduction(totalDeduction);
		obj.setTotalAllowance(totalAllowance);
		obj.setCurrentTaxAllowance(currentTaxAllowance);
		obj.setCurrentTaxGross(currentTaxGross);
		obj.setCurrentTaxPenalty(currentTaxPenalty);
			
		return obj;
	}
	
	@Override
	public void saveListData(List<PayrollElementHeaderYearly> listData){
		for(PayrollElementHeaderYearly e : listData){
			System.out.println("After Ext Id " + e.getExtId());
			PayrollElementHeaderYearly payrollElementHeaderYearly = payrollElementHeaderYearlyService.findByExtId(e.getExtId());
			
			if(payrollElementHeaderYearly == null){
				payrollElementHeaderYearly = new PayrollElementHeaderYearly() ;
				payrollElementHeaderYearly.setCreatedDate(new Date());
				payrollElementHeaderYearly.setCreatedBy("Talents Gateway");
				payrollElementHeaderYearly.setExtId(e.getExtId());
			}
			
			payrollElementHeaderYearly.setCurrentYear(e.getCurrentYear());
			payrollElementHeaderYearly.setTaxPaid(e.getTaxPaid());
			payrollElementHeaderYearly.setTaxStatus(e.getTaxStatus());
			payrollElementHeaderYearly.setTaxType(e.getTaxType());
			payrollElementHeaderYearly.setEmploymentExtId(e.getEmploymentExtId());
			payrollElementHeaderYearly.setPph21DipotongDilunasi(e.getPph21DipotongDilunasi());
			payrollElementHeaderYearly.setPph21Terhutang(e.getPph21Terhutang());
			payrollElementHeaderYearly.setJumlahPhBruto(e.getJumlahPhBruto());
			payrollElementHeaderYearly.setTotalDeduction(e.getTotalDeduction());
			payrollElementHeaderYearly.setTotalAllowance(e.getTotalAllowance());
			payrollElementHeaderYearly.setCurrentTaxAllowance(e.getCurrentTaxAllowance());
			payrollElementHeaderYearly.setCurrentTaxGross(e.getCurrentTaxGross());
			payrollElementHeaderYearly.setCurrentTaxPenalty(e.getCurrentTaxPenalty());
			Employment employment = null;
			if(e.getEmploymentExtId() != null)
				employment = employmentService.findByExtId(e.getEmploymentExtId());
			
			
			payrollElementHeaderYearly.setEmployment(employment);
			payrollElementHeaderYearly.setCompany(this.companyid);
			payrollElementHeaderYearly.setModifiedDate(new Date());
			payrollElementHeaderYearly.setModifiedBy("Talents Gateway");
			payrollElementHeaderYearlyService.save(payrollElementHeaderYearly);
			
			System.out.println("PayrollElementHeader Save Address");
		}
	}
	
}
