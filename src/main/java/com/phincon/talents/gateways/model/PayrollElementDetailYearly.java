package com.phincon.talents.gateways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hr_payroll_element_detail_yearly")
public class PayrollElementDetailYearly extends AbstractEntity {

	
	@Column(name="current_year", length=50)
	private String currentYear;
	
	
	@Column(name="element_group", length=50)
	private String elementGroup;
	
	@Column(name="element_name_ext_id", length=50)
	private String elementNameExtId;
	
	@Column(name="element_type", length=50)
	private String elementType;
	
	@Column(name="total")
	private Double total;
	
//	@ManyToOne
//	@JoinColumn(name="payroll_element_header__yearly_id")
//	private PayrollElementHeaderYearly payrollElementHeaderYearly;
	

	
	@Column(name="payroll_element_header_yearly_id")
	private Long payrollElementHeaderYearly;
	
	
	@Column(name="payroll_element_header_yearly_ext_id", length=100)
	private String payrollElementHeaderYearlyExtId;

	@Column(name="ack_sync")
	private Boolean ackSync;
	
	
	
	public Boolean getAckSync() {
		return ackSync;
	}

	public void setAckSync(Boolean ackSync) {
		this.ackSync = ackSync;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getPayrollElementHeaderYearly() {
		return payrollElementHeaderYearly;
	}

	public void setPayrollElementHeaderYearly(
			Long payrollElementHeaderYearly) {
		this.payrollElementHeaderYearly = payrollElementHeaderYearly;
	}

	public String getPayrollElementHeaderYearlyExtId() {
		return payrollElementHeaderYearlyExtId;
	}

	public void setPayrollElementHeaderYearlyExtId(
			String payrollElementHeaderYearlyExtId) {
		this.payrollElementHeaderYearlyExtId = payrollElementHeaderYearlyExtId;
	}

	public String getElementNameExtId() {
		return elementNameExtId;
	}

	public void setElementNameExtId(String elementNameExtId) {
		this.elementNameExtId = elementNameExtId;
	}
	
	
	
	


	
	
	
}
