package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Assignment;

@Repository
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long>{
	
	@Query
	Assignment findByExtId(String extId);
	
	
	@Query("SELECT u FROM Assignment u WHERE u.extId is NULL")
	List<Assignment> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Assignment SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
}
