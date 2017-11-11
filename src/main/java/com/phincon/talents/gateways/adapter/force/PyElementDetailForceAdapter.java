package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementDetail;
import com.phincon.talents.gateways.model.PayrollElementHeader;
import com.phincon.talents.gateways.services.AssignmentService;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.services.EmploymentService;
import com.phincon.talents.gateways.services.PayrollElementDetailService;
import com.phincon.talents.gateways.services.PayrollElementHeaderService;

@Service
public class PyElementDetailForceAdapter extends ForceAdapter<PayrollElementDetail> {

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
	
	
	public PyElementDetailForceAdapter() {
		super();
	}
	
	@Override
	protected PayrollElementDetail convertMapResultToObject(Map<String, Object> mapResult){
		String budgetItemCode =(String) mapResult.get("Budget_Item_Code__c");   
		String extId =(String) mapResult.get("Id");   
		String name =(String) mapResult.get("Name");   
		String effectiveDate =(String) mapResult.get("Effective_Date__c"); 
		String elementCode =(String) mapResult.get("Element_Code__c");   
		String elementGroup =(String) mapResult.get("Element_Group__c");   
		String elementName =(String) mapResult.get("Element_Name__c"); 
		String elementType =(String) mapResult.get("Element_Type__c"); 
		Double elementValue =(Double) mapResult.get("Element_Value__c"); 
		Boolean groupDisplay =(Boolean) mapResult.get("Group_Display__c"); 
		Double groupOrder =(Double) mapResult.get("Group_Order__c"); 
		Boolean isNet =(Boolean) mapResult.get("Is_Net__c");  
		Boolean onPaySlip =(Boolean) mapResult.get("On_Pay_Slip__c"); 
		String payrollElement =(String) mapResult.get("Payroll_Element__c");  
		Double prevElementValueResult =(Double) mapResult.get("Previous_Element_Value_Result__c");   
		String elementHeaderExtId =(String) mapResult.get("Pre_MTD_Header_ID__c"); 
		String remark =(String) mapResult.get("Remark__c"); 
		String tax =(String) mapResult.get("Tax__c"); 
		Double totalElementValue =(Double) mapResult.get("Total_Element_Value__c");   
		
		 
		 PayrollElementDetail obj = new PayrollElementDetail();
		 obj.setExtId(extId);
		 obj.setBudgetItemCode(budgetItemCode);
		 obj.setName(name);
		 obj.setEffectiveDate(effectiveDate);
		 obj.setElementCode(elementCode);
		 obj.setElementGroup(elementGroup);
		 obj.setElementName(elementName);
		 obj.setElementType(elementType);
		 obj.setElementValue(elementValue);
		 obj.setGroupDisplay(groupDisplay);
		 obj.setGroupOrder(groupOrder);
		 obj.setIsNet(isNet);
		 obj.setOnPaySlip(onPaySlip);
		 obj.setPayrollElement(payrollElement);
		 obj.setPrevElementValue(prevElementValueResult);
		 obj.setPayrollElementHeaderExtId(elementHeaderExtId); // Header Element
		 obj.setRemark(remark);
		 obj.setTax(tax);
		 obj.setTotalElement(totalElementValue);
		 
		return obj;
	}
	
	@Override
	public void saveListData(List<PayrollElementDetail> listData, boolean isInit){
		for(PayrollElementDetail e : listData){
		
			PayrollElementDetail payrollElementDetail = payrollElementDetailService.findByExtId(e.getExtId());
			
			if(payrollElementDetail == null){
				payrollElementDetail = new PayrollElementDetail() ;
				payrollElementDetail.setCreatedDate(new Date());
				payrollElementDetail.setCreatedBy("Talents Gateway");
				payrollElementDetail.setExtId(e.getExtId());
			}
			
			if(isInit)
				payrollElementDetail.setAckSync(false);
			
			payrollElementDetail.setExtId(e.getExtId());
			 payrollElementDetail.setBudgetItemCode(e.getBudgetItemCode());
			 payrollElementDetail.setName(e.getName());
			 payrollElementDetail.setEffectiveDate(e.getEffectiveDate());
			 payrollElementDetail.setElementCode(e.getElementCode());
			 payrollElementDetail.setElementGroup(e.getElementGroup());
			 payrollElementDetail.setElementName(e.getElementName());
			 payrollElementDetail.setElementType(e.getElementType());
			 payrollElementDetail.setElementValue(e.getElementValue());
			 payrollElementDetail.setGroupDisplay(e.getGroupDisplay());
			 payrollElementDetail.setGroupOrder(e.getGroupOrder());
			 payrollElementDetail.setIsNet(e.getIsNet());
			 payrollElementDetail.setOnPaySlip(e.getOnPaySlip());
			 payrollElementDetail.setPayrollElement(e.getPayrollElement());
			 payrollElementDetail.setPrevElementValue(e.getPrevElementValue());
			 payrollElementDetail.setPayrollElementHeaderExtId(e.getPayrollElementHeaderExtId()); // Header Element
			 payrollElementDetail.setRemark(e.getRemark());
			 payrollElementDetail.setTax(e.getTax());
			 payrollElementDetail.setTotalElement(e.getTotalElement());
			 if(e.getPayrollElementHeaderExtId() != null){
					PayrollElementHeader payrollElementHeader = payrollElementHeaderService.findByExtId(e.getPayrollElementHeaderExtId());
					payrollElementDetail.setPayrollElementHeader(payrollElementHeader);
			 }
			payrollElementDetail.setModifiedDate(new Date());
			payrollElementDetail.setModifiedBy("Talents Gateway");
			payrollElementDetailService.save(payrollElementDetail);
			System.out.println("PayrollElementDetail Save");
		}
	}
	
}
