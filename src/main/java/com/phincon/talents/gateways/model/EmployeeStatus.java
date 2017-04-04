package com.phincon.talents.gateways.model;

import javax.persistence.Column;

public class EmployeeStatus {
	private String name;

	@Column(name = "company_id")
	private Long company;

	@Column(name = "ext_id")
	private String extId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

}
