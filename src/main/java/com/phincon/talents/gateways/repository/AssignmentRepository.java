package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

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
	@Query("UPDATE Assignment SET ackSync=true, extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Query("SELECT extId,uuidStr FROM Assignment where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();


	@Modifying
	@Query("UPDATE Assignment SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE Assignment SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") String extIdIn);

}
