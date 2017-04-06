package com.phincon.talents.gateways.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="h_last_sync")
public class HistorySync extends AbstractEntity{
	
	@Column(name="company_id", length=20)
	private String companyId;
	
	@Column(name="module_name", length=100)
	private String moduleName;
	
	@Column(name="last_sync")
	private Timestamp lastSync;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Timestamp getLastSync() {
		return lastSync;
	}
	public void setLastSync(Timestamp lastSync) {
		this.lastSync = lastSync;
	}
}
