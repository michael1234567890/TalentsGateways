package com.phincon.talents.gateways.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users extends AbstractEntity{

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getApikey() {
		return apikey;
	}

	public void setApikey(Integer apikey) {
		this.apikey = apikey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getReset_password_key() {
		return reset_password_key;
	}

	public void setReset_password_key(String reset_password_key) {
		this.reset_password_key = reset_password_key;
	}

	public String getActivation_key() {
		return activation_key;
	}

	public void setActivation_key(String activation_key) {
		this.activation_key = activation_key;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUuid_str() {
		return uuid_str;
	}

	public void setUuid_str(String uuid_str) {
		this.uuid_str = uuid_str;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getEmployee_ext_id() {
		return employee_ext_id;
	}

	public void setEmployee_ext_id(String employee_ext_id) {
		this.employee_ext_id = employee_ext_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPhoto_profile() {
		return photo_profile;
	}

	public void setPhoto_profile(String photo_profile) {
		this.photo_profile = photo_profile;
	}

	public String getLast_login_address() {
		return last_login_address;
	}

	public void setLast_login_address(String last_login_address) {
		this.last_login_address = last_login_address;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public boolean isIs_leader() {
		return is_leader;
	}

	public void setIs_leader(boolean is_leader) {
		this.is_leader = is_leader;
	}

	public boolean isIs_change_password() {
		return is_change_password;
	}

	public void setIs_change_password(boolean is_change_password) {
		this.is_change_password = is_change_password;
	}

	public boolean isIs_hr() {
		return is_hr;
	}

	public void setIs_hr(boolean is_hr) {
		this.is_hr = is_hr;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getMobile_version() {
		return mobile_version;
	}

	public void setMobile_version(Integer mobile_version) {
		this.mobile_version = mobile_version;
	}

	
	@Column(name = "username", length = 100)
	private String username;

	@Column(name = "version")
	private Integer version;
	
	@Column(name = "apikey")
	private Integer apikey;

	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "password", length = 100)
	private String password;
		
	@Column(name = "activated")
	private boolean	 activated;
	
	@Column(name = "reset_password_key", length = 100)
	private String reset_password_key;

	@Column(name = "activation_key", length = 100)
	private String activation_key;

	@Column(name = "first_name", length = 100)
	private String first_name;

	@Column(name = "last_name", length = 100)
	private String last_name;

	@Column(name = "uuid_str", length = 255)
	private String uuid_str;
	
	@Column(name = "employee_id")
	private Long employee_id;

	@Column(name = "company_id")
	private Long company_id;

	@Column(name = "employee_ext_id", length = 255)
	private String employee_ext_id;
	  
	@Column(name = "full_name", length = 200)
	private String full_name;

	@Column(name = "photo_profile", length = 255)
	private String photo_profile;

	@Column(name = "last_login_address", length = 100)
	private String last_login_address;

	@Column(name = "last_login_time")
	private Date last_login_time;

	@Column(name = "user_agent", length = 255)
	private String user_agent;

	@Column(name = "is_admin")
	private boolean is_admin;

	@Column(name = "is_leader")
	private boolean is_leader;

	@Column(name = "is_change_password")
	private boolean is_change_password;

	@Column(name = "is_hr")
	private boolean is_hr;
	
	@Column(name = "priority")
	private Integer priority;

	@Column(name = "mobile_version")
	private Integer mobile_version;
	
	
}
