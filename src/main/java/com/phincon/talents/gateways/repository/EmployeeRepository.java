package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{
	
	
	 @Query
	 Employee findByExtId(String extId);
	 
	
}
