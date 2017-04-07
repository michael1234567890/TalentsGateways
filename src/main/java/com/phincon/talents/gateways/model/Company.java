package com.phincon.talents.gateways.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
@NamedQueries({
    @NamedQuery(
            name = Company.FIND_BY_CODE,
            query = "select bean from Company bean where code = :code"
    )
})
public class Company extends AbstractEntity {
	public static final String FIND_BY_CODE = "company.findByCode";
	public static final String FIND_ALL = "company.findAll";
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="code", length=100)
	private String code;
	
	@Column(name="address", length=255)
	private String address;
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Company(){}
	

	
	 @Override
	    public String toString() {
	        return "Company{" +
	                "name='" + name + '\'' + "}";
	    }
	
}
