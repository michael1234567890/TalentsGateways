package com.phincon.talents.gateways.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="h_last_sync")
public class HistorySync extends AbstractEntity{
	
	@Column(name="company_id", length=20)
	private Long companyId;
	
	@Column(name="module_name", length=100)
	private String moduleName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_sync")
	private Date lastSync;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Date getLastSync() {
		return lastSync;
	}
	public void setLastSync(Date lastSync) {
		this.lastSync = lastSync;
	}
}
