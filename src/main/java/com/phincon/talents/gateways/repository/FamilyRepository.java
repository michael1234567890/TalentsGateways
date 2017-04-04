package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;

@Repository
public interface FamilyRepository extends PagingAndSortingRepository<Family,Long>{
	
	
	 @Query
	 Family findByExtId(String extId);
	 
	
}
