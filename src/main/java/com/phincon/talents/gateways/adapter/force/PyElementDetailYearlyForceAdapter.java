package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementDetailYearly;
import com.phincon.talents.gateways.model.PayrollElementHeaderYearly;
import com.phincon.talents.gateways.repository.PayrollElementDetailYearlyRepository;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.PayrollElementDetailYearlyService;
import com.phincon.talents.gateways.services.PayrollElementHeaderYearlyService;

@Service
public class PyElementDetailYearlyForceAdapter extends ForceAdapter<PayrollElementDetailYearly> {

	@Autowired
	PayrollElementHeaderYearlyService payrollElementHeaderYearlyService;
	

	@Autowired
	PayrollElementDetailYearlyRepository payrollElementDetailYearlyRepository;
	
	@Autowired
	PayrollElementDetailYearlyService payrollElementDetailYearlyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	
	public PyElementDetailYearlyForceAdapter() {
		super();
	}
	
	@Override
	protected PayrollElementDetailYearly convertMapResultToObject(Map<String, Object> mapResult){
		
		Double total = (Double) mapResult.get("Total");
		String extHeaderId = (String) mapResult.get("Result_YTD_Header_ID__c");
		String elementGroup = (String) mapResult.get("Element_Group__c");
		String elementType = (String) mapResult.get("Element_Type__c");
		String currentYear = (String) mapResult.get("Current_Year__c");
		String elementNameExtId = (String) mapResult.get("Element_Name__c");
		
		PayrollElementDetailYearly obj = new PayrollElementDetailYearly();
		obj.setElementNameExtId(elementNameExtId);
		obj.setTotal(total);
		obj.setPayrollElementHeaderYearlyExtId(extHeaderId);
		obj.setCurrentYear(currentYear);
		obj.setElementGroup(elementGroup);
		obj.setElementType(elementType);
		 
		return obj;
	}
	
	@Override
	public void saveListData(List<PayrollElementDetailYearly> listData){
		for(PayrollElementDetailYearly e : listData){
			System.out.println("After Ext Id " + e.getExtId());
			PayrollElementDetailYearly payrollElementDetailYearly = null;
			List<PayrollElementDetailYearly> listpayrollElementDetailYearly = payrollElementDetailYearlyRepository.findByPayrollElementHeaderYearlyExtIdAndElementGroupAndElementType(e.getPayrollElementHeaderYearlyExtId(),e.getElementGroup(),e.getElementType());
			if(listpayrollElementDetailYearly != null && listpayrollElementDetailYearly.size() > 0)
				payrollElementDetailYearly = listpayrollElementDetailYearly.get(0);
			
			if(payrollElementDetailYearly == null){
				payrollElementDetailYearly = new PayrollElementDetailYearly() ;
				payrollElementDetailYearly.setCreatedDate(new Date());
				payrollElementDetailYearly.setCreatedBy("Talents Gateway");
				payrollElementDetailYearly.setExtId(e.getExtId());
			}
			payrollElementDetailYearly.setElementNameExtId(e.getElementNameExtId());
			payrollElementDetailYearly.setTotal(e.getTotal());
			payrollElementDetailYearly.setPayrollElementHeaderYearlyExtId(e.getPayrollElementHeaderYearlyExtId());
			PayrollElementHeaderYearly header = null;
			payrollElementDetailYearly.setPayrollElementHeaderYearly(null);
			if(e.getPayrollElementHeaderYearlyExtId() != null){
				header = payrollElementHeaderYearlyService.findByExtId(e.getPayrollElementHeaderYearlyExtId());
				if(header!= null) 
					payrollElementDetailYearly.setPayrollElementHeaderYearly(header.getId());
			}
			payrollElementDetailYearly.setCurrentYear(e.getCurrentYear());
			payrollElementDetailYearly.setElementGroup(e.getElementGroup());
			payrollElementDetailYearly.setElementType(e.getElementType());
			payrollElementDetailYearlyRepository.save(payrollElementDetailYearly);
			System.out.println("PayrollElementDetailYearly Save ");
		}
	}
	
}
