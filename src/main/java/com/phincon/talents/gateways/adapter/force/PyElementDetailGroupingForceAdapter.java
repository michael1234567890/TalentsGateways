package com.phincon.talents.gateways.adapter.force;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementDetail;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.PayrollElementDetailService;
import com.phincon.talents.gateways.services.PayrollElementHeaderService;

@Service
public class PyElementDetailGroupingForceAdapter extends ForceDetailGroupingAdapter<PayrollElementDetail> {

	@Autowired
	PayrollElementHeaderService payrollElementHeaderService;
	

	@Autowired
	PayrollElementDetailService payrollElementDetailService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmploymentService employmentService;
	
	@Autowired
	AssignmentService assignmentService;
	
	
	public PyElementDetailGroupingForceAdapter() {
		super();
	}
	
	@Override
	protected PayrollElementDetail convertMapResultToObject(Map<String, Object> mapResult){
		
//		String elementGroup =(String) mapResult.get("Element_Group__c");   //
//		String elementName =(String) mapResult.get("Element_Name__c");  //
//		String elementType =(String) mapResult.get("Element_Type__c");  //
//		Double elementValue =(Double) mapResult.get("Element_Value__c");  //Total
//		
//		String elementHeaderExtId =(String) mapResult.get("Pre_MTD_Header_ID__c");  //
//		String periodDate =(String) mapResult.get("Payment_Start_Date__c");  // 
//		// Double totalElementValue =(Double) mapResult.get("Total_Element_Value__c");   
//		
//		 
		System.out.println(mapResult.keySet());
		PayrollElementDetail obj = new PayrollElementDetail();
		
		 
		return obj;
	}
	
	@Override
	public void saveListDate(List<PayrollElementDetail> listData){
//		for(PayrollElementDetail e : listData){
//		
//			PayrollElementDetail payrollElementDetail = payrollElementDetailService.findByExtId(e.getExtId());
//			
//			if(payrollElementDetail == null){
//				payrollElementDetail = new PayrollElementDetail() ;
//				payrollElementDetail.setCreatedDate(new Date());
//				payrollElementDetail.setCreatedBy("Talents Gateway");
//				payrollElementDetail.setExtId(e.getExtId());
//			}
//			
//			payrollElementDetail.setExtId(e.getExtId());
//			 payrollElementDetail.setBudgetItemCode(e.getBudgetItemCode());
//			 payrollElementDetail.setName(e.getName());
//			 payrollElementDetail.setEffectiveDate(e.getEffectiveDate());
//			 payrollElementDetail.setElementCode(e.getElementCode());
//			 payrollElementDetail.setElementGroup(e.getElementGroup());
//			 payrollElementDetail.setElementName(e.getElementName());
//			 payrollElementDetail.setElementType(e.getElementType());
//			 payrollElementDetail.setElementValue(e.getElementValue());
//			 payrollElementDetail.setGroupDisplay(e.getGroupDisplay());
//			 payrollElementDetail.setGroupOrder(e.getGroupOrder());
//			 payrollElementDetail.setIsNet(e.getIsNet());
//			 payrollElementDetail.setOnPaySlip(e.getOnPaySlip());
//			 payrollElementDetail.setPayrollElement(e.getPayrollElement());
//			 payrollElementDetail.setPrevElementValue(e.getPrevElementValue());
//			 payrollElementDetail.setPayrollElementHeaderExtId(e.getPayrollElementHeaderExtId()); // Header Element
//			 payrollElementDetail.setRemark(e.getRemark());
//			 payrollElementDetail.setTax(e.getTax());
//			 payrollElementDetail.setTotalElement(e.getTotalElement());
//			 
//			PayrollElementHeader payrollElementHeader = payrollElementHeaderService.findByExtId(e.getPayrollElementHeaderExtId());
//			payrollElementDetail.setPayrollElementHeader(payrollElementHeader);
//			
//			
//			
//			payrollElementDetail.setModifiedDate(new Date());
//			payrollElementDetail.setModifiedBy("Talents Gateway");
//			payrollElementDetailService.save(payrollElementDetail);
//			
//			System.out.println("PayrollElementDetail Save");
//		}
	}
	
}
