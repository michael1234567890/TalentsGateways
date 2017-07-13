package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.PayrollElementHeaderYearly;

@Repository
public interface PayrollElementHeaderYearlyRepository extends PagingAndSortingRepository<PayrollElementHeaderYearly,Long>{
	
	@Query
	PayrollElementHeaderYearly findByExtId(String extId);
	
	
	
}
