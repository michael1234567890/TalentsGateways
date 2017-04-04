package com.phincon.talents.gateways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="hr_address")
public class Address extends AbstractEntity {

	@Column(name="address", length=255)
	private String address;
	
	@Column(name="distance", length=10)
	private String distance;
	
	@Column(name="address_status", length=30)
	private String addressStatus;
	
	@Column(name="type", length=30)
	private String type;
	
	@Column(name="city", length=50)
	private String city;
	
	@Column(name="country", length=50)
	private String country;
	
	@Column(name="district", length=100)
	private String district;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="phone", length=20)
	private String phone;
	
	@Column(name="residence", length=255)
	private String residence;
	
	@Column(name="rt", length=10)
	private String rt;
	
	@Column(name="rw", length=10)
	private String rw;
	
	@Column(name="province", length=50)
	private String province;
	
	@Column(name="stay_status", length=30)
	private String stayStatus;
	
	@Column(name="subdistrict", length=100)
	private String subdistrict;
	
	@Column(name="zip_code", length=15)
	private String zipCode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getAddressStatus() {
		return addressStatus;
	}

	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getStayStatus() {
		return stayStatus;
	}

	public void setStayStatus(String stayStatus) {
		this.stayStatus = stayStatus;
	}

	public String getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}
