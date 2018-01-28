package com.phincon.talents.gateways.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sync_settings")
public class SyncSettings extends AbstractEntity{
	
	@Column(name = "module_name", length=100)
	private String moduleName;
	
	@Column(name = "sync_type", length=100)
	private String syncType;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_sync_date")
	private Date lastSyncDate;
	
	@Column(name = "sync_days")
	private Integer syncDays;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_sync_date_adhoc")
	private Date lastSyncDateAdhoc;
	
	@Column(name = "sync_days_adhoc")
	private Integer synDaysAdhoc;
	
	@Column(name = "is_adhoc")
	private Boolean isAdhoc;
	
	@Column(name = "company_id")
	private Long company;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getLastSyncDate() {
		return lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		this.lastSyncDate = lastSyncDate;
	}

	public Integer getSyncDays() {
		return syncDays;
	}

	public void setSyncDays(Integer syncDays) {
		this.syncDays = syncDays;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Date getLastSyncDateAdhoc() {
		return lastSyncDateAdhoc;
	}

	public void setLastSyncDateAdhoc(Date lastSyncDateAdhoc) {
		this.lastSyncDateAdhoc = lastSyncDateAdhoc;
	}

	public Integer getSynDaysAdhoc() {
		return synDaysAdhoc;
	}

	public void setSynDaysAdhoc(Integer synDaysAdhoc) {
		this.synDaysAdhoc = synDaysAdhoc;
	}

	public Boolean getIsAdhoc() {
		return isAdhoc;
	}

	public void setIsAdhoc(Boolean isAdhoc) {
		this.isAdhoc = isAdhoc;
	}

	public String getSyncType() {
		return syncType;
	}

	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}
	
	
	
}
