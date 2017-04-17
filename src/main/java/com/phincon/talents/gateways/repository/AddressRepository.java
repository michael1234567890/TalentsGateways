package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Address;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long>{
	
	@Query
	Address findByExtId(String extId);
	
	@Query("SELECT u FROM Address u WHERE u.extId is NULL")
	List<Address> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Address SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
}
