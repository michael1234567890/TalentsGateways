package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Grade;

@Repository
public interface GradeRepository extends PagingAndSortingRepository<Grade, Long>{
	@Query
	Grade findByExtId(String extId);
	
	@Query("SELECT u FROM Grade u WHERE u.extId is NULL")
	List<Grade> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Grade SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Grade SET extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);

	
	@Query("SELECT extId,uuidStr FROM Grade where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();


	@Modifying
	@Query("UPDATE Grade SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE Grade SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") String extIdIn);

}
