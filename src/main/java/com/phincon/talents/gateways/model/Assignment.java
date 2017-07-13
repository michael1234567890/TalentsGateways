package com.phincon.talents.gateways.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hr_assignment")
public class Assignment extends AbstractEntity {
	public static String MODEDIRECT = "direct";
	public static String MODEINDIRECT = "indirect";
	public static String MODEOTHER = "other";

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "job_title")
//	private JobTitle jobTitle;
	
	@Column(name="job_title_id")
	private Long jobTitle;
	
	@Column(name="job_title_ext_id")
	private String jobTitleExtId;
	
	
	@Column(name="position_id")
	private Long position;
	
	@Column(name="position_ext_id")
	private String positionExtId;
	
	@Column(name="grade_id")
	private Long grade;
	
	@Column(name="grade_ext_id")
	private String gradeExtId;
	
	
	
	@Column(name = "employment_id")
	private Long employment;

	/*
	 * 
	 */
	@Column(name = "employment_direct_to_id")
	private Long employmentDirectTo;
	
	@Column(name = "employment_direct_to_ext_id")
	private String employmentDirectToExtId;

	@Column(name = "company_id")
	private Long company;

	@Column(name = "mode", length = 20)
	private String mode;

	@Column(name = "description", length = 200)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "deleted")
	private Boolean deleted = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "letter_date")
	private Date letterDate;
	
	@Column(name = "letter_no", length = 100)
	private String letterNo;
	
	
	@Column(name = "work_location", length = 100)
	private String workLocation;

	@Column(name = "home_base", length = 100)
	private String homeBase;

	@Column(name = "department", length = 100)
	private String deparment;
	
	@Column(name = "sub_department", length = 100)
	private String subDeparment;

	@Column(name = "division", length = 100)
	private String division;

	@Column(name = "level", length = 100)
	private String level;

	@Column(name = "category", length = 100)
	private String category;
	
	@Column(name = "employee_type", length = 50)
	private String employeeType;
	
	@Column(name = "employee_status", length = 50)
	private String employeeStatus;
	
	@Column(name = "name", length = 50)
	private String name;
	
	

	@Column(name = "employment_ext_id", length=100)
	private String employmentExtId;
	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getHomeBase() {
		return homeBase;
	}

	public void setHomeBase(String homeBase) {
		this.homeBase = homeBase;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	

	public Long getEmploymentDirectTo() {
		return employmentDirectTo;
	}

	public void setEmploymentDirectTo(Long employmentDirectTo) {
		this.employmentDirectTo = employmentDirectTo;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	

	

	public Long getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Long jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobTitleExtId() {
		return jobTitleExtId;
	}

	public void setJobTitleExtId(String jobTitleExtId) {
		this.jobTitleExtId = jobTitleExtId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmploymentExtId() {
		return employmentExtId;
	}

	public void setEmploymentExtId(String employmentExtId) {
		this.employmentExtId = employmentExtId;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getSubDeparment() {
		return subDeparment;
	}

	public void setSubDeparment(String subDeparment) {
		this.subDeparment = subDeparment;
	}

	public String getEmploymentDirectToExtId() {
		return employmentDirectToExtId;
	}

	public void setEmploymentDirectToExtId(String employmentDirectToExtId) {
		this.employmentDirectToExtId = employmentDirectToExtId;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getPositionExtId() {
		return positionExtId;
	}

	public void setPositionExtId(String positionExtId) {
		this.positionExtId = positionExtId;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public String getGradeExtId() {
		return gradeExtId;
	}

	public void setGradeExtId(String gradeExtId) {
		this.gradeExtId = gradeExtId;
	}

	
	
	
	



}
