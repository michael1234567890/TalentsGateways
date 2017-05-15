package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.PayrollElementHeader;

@Repository
public interface PayrollElementHeaderRepository extends PagingAndSortingRepository<PayrollElementHeader,Long>{
	
	@Query
	PayrollElementHeader findByExtId(String extId);
	
	
	
}
