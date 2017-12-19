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
	public final static int DIFF_SYNC = 60; // in sec
	@Column(name="company_id", length=20)
	private Long companyId;
	
	@Column(name="module_name", length=100)
	private String moduleName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_sync")
	private Date startSync;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_sync")
	private Date endSync;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_sync")
	private Date lastSync;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="next_sync_time")
	private Date nextSyncTime;
	
	
	@Column(name="diff_next_sync")
	private int diffNextSync = DIFF_SYNC; //in sec
	
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
	public int getDiffNextSync() {
		return diffNextSync;
	}
	public void setDiffNextSync(int diffNextSync) {
		this.diffNextSync = diffNextSync;
	}
	public Date getNextSyncTime() {
		return nextSyncTime;
	}
	public void setNextSyncTime(Date nextSyncTime) {
		this.nextSyncTime = nextSyncTime;
	}
	public Date getStartSync() {
		return startSync;
	}
	public void setStartSync(Date startSync) {
		this.startSync = startSync;
	}
	public Date getEndSync() {
		return endSync;
	}
	public void setEndSync(Date endSync) {
		this.endSync = endSync;
	}
	
	
	
	
}
