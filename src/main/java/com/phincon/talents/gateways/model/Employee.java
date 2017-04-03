package com.phincon.talents.gateways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hr_employee")
public class Employee extends AbstractEntity{

	
	@Column(name = "first_name", length=100)
	private String firstName;



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "Employee [ firstName=" + firstName + "]";
	}
	
	
	
	

	
}
