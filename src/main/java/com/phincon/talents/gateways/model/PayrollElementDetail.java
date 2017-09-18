package com.phincon.talents.gateways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hr_payroll_element_detail")
public class PayrollElementDetail extends AbstractEntity {

	@Column(name="budget_item_code", length=50)
	private String budgetItemCode;
	
//	@Temporal(TemporalType.DATE)
//	@Column(name="effective_date", length=50)
//	private Date effectiveDate;
	
	@Column(name="effective_date", length=50)
	private String effectiveDate;
	
	@Column(name="element_code", length=50)
	private String elementCode;
	
	@Column(name="element_group_code", length=50)
	private String elementGroupCode;
	
	@Column(name="element_group", length=50)
	private String elementGroup;
	
	@Column(name="element_type", length=50)
	private String elementType;
	
	@Column(name="element_value",length=50)
	private Double elementValue;
	
	@Column(name="element_name",length=50)
	private String elementName;
	
	@Column(name="group_display",length=50)
	private Boolean groupDisplay;

	@Column(name="group_order")
	private Double groupOrder;
	

	@Column(name="is_net")
	private Boolean isNet;

	@Column(name="on_pay_slip")
	private Boolean onPaySlip;
	
	@Column(name="payroll_element", length=100)
	private String payrollElement;
	
	@ManyToOne
	@JoinColumn(name="payroll_element_header_id")
	private PayrollElementHeader payrollElementHeader;
	
	@Column(name="payroll_element_header_ext_id", length=100)
	private String payrollElementHeaderExtId;
	
	@Column(name="prev_element_value")
	private Double prevElementValue;
	
	@Column(name="remark", length=100)
	private String remark;
	
	@Column(name="total_element")
	private Double totalElement;
	
	
	@Column(name="tax", length=50)
	private String tax;
	
	@Column(name="name", length=50)
	private String name;

	@Column(name="ack_sync")
	private Boolean ackSync;
	

	
	public Boolean getAckSync() {
		return ackSync;
	}


	public void setAckSync(Boolean ackSync) {
		this.ackSync = ackSync;
	}


	public String getBudgetItemCode() {
		return budgetItemCode;
	}


	public void setBudgetItemCode(String budgetItemCode) {
		this.budgetItemCode = budgetItemCode;
	}


	public String getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	public String getElementCode() {
		return elementCode;
	}


	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}


	public String getElementGroupCode() {
		return elementGroupCode;
	}


	public void setElementGroupCode(String elementGroupCode) {
		this.elementGroupCode = elementGroupCode;
	}


	public String getElementGroup() {
		return elementGroup;
	}


	public void setElementGroup(String elementGroup) {
		this.elementGroup = elementGroup;
	}


	public String getElementType() {
		return elementType;
	}


	public void setElementType(String elementType) {
		this.elementType = elementType;
	}


	public Double getElementValue() {
		return elementValue;
	}


	public void setElementValue(Double elementValue) {
		this.elementValue = elementValue;
	}


	public Boolean getGroupDisplay() {
		return groupDisplay;
	}


	public void setGroupDisplay(Boolean groupDisplay) {
		this.groupDisplay = groupDisplay;
	}


	public Double getGroupOrder() {
		return groupOrder;
	}


	public void setGroupOrder(Double groupOrder) {
		this.groupOrder = groupOrder;
	}


	public Boolean getIsNet() {
		return isNet;
	}


	public void setIsNet(Boolean isNet) {
		this.isNet = isNet;
	}


	public Boolean getOnPaySlip() {
		return onPaySlip;
	}


	public void setOnPaySlip(Boolean onPaySlip) {
		this.onPaySlip = onPaySlip;
	}


	public String getPayrollElement() {
		return payrollElement;
	}


	public void setPayrollElement(String payrollElement) {
		this.payrollElement = payrollElement;
	}


	public PayrollElementHeader getPayrollElementHeader() {
		return payrollElementHeader;
	}


	public void setPayrollElementHeader(PayrollElementHeader payrollElementHeader) {
		this.payrollElementHeader = payrollElementHeader;
	}


	public Double getPrevElementValue() {
		return prevElementValue;
	}


	public void setPrevElementValue(Double prevElementValue) {
		this.prevElementValue = prevElementValue;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Double getTotalElement() {
		return totalElement;
	}


	public void setTotalElement(Double totalElement) {
		this.totalElement = totalElement;
	}


	public String getTax() {
		return tax;
	}


	public void setTax(String tax) {
		this.tax = tax;
	}
	
	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	


	public String getElementName() {
		return elementName;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public String getPayrollElementHeaderExtId() {
		return payrollElementHeaderExtId;
	}


	public void setPayrollElementHeaderExtId(String payrollElementHeaderExtId) {
		this.payrollElementHeaderExtId = payrollElementHeaderExtId;
	}


	@Override
	public String toString() {
		return "PayrollElementDetail [budgetItemCode=" + budgetItemCode
				+ ", effectiveDate=" + effectiveDate + ", elementCode="
				+ elementCode + ", elementGroupCode=" + elementGroupCode
				+ ", elementGroup=" + elementGroup + ", elementType="
				+ elementType + ", elementValue=" + elementValue
				+ ", groupDisplay=" + groupDisplay + ", groupOrder="
				+ groupOrder + ", isNet=" + isNet + ", onPaySlip=" + onPaySlip
				+ ", payrollElement=" + payrollElement
				+ ", payrollElementHeader=" + payrollElementHeader
				+ ", prevElementValue=" + prevElementValue + ", remark="
				+ remark + ", totalElement=" + totalElement + ", tax=" + tax
				+ ", name=" + name + "]";
	}
	
	
	


	
	
	
}
