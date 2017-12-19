package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.PayrollElementHeader;

@Repository
public interface PayrollElementHeaderRepository extends PagingAndSortingRepository<PayrollElementHeader,Long>{
	
	@Query
	PayrollElementHeader findByExtId(String extId);
	
	
	@Query("SELECT extId,uuidStr FROM PayrollElementHeader where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();
	
	@Modifying
	@Query("UPDATE PayrollElementHeader SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE PayrollElementHeader SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") String extIdIn);

}
