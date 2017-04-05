package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.phincon.talents.gateways.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long>{
	
	@Query
	Address findByExtId(String extId);
}
