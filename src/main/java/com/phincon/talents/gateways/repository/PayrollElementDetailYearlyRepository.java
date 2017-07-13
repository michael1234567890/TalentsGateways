package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.PayrollElementDetailYearly;

@Repository
public interface PayrollElementDetailYearlyRepository extends PagingAndSortingRepository<PayrollElementDetailYearly,Long>{
	
	
	
	@Query
	PayrollElementDetailYearly findByExtId(String extId);
	
	@Query
	List<PayrollElementDetailYearly> findByPayrollElementHeaderYearlyExtIdAndElementGroupAndElementType(String headerExtId,String elementGroup, String elementType);
}
