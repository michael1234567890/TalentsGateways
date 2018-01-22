package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.phincon.talents.gateways.model.AtempDaily;

public interface AtempDailyRepository extends PagingAndSortingRepository<AtempDaily, Long>{

	@Query
	AtempDaily findByExtId(String extId);
	
	@Query("SELECT u FROM AtempDaily u WHERE u.needSync=true")
	List<AtempDaily> findNeedSync();
	
	@Query("SELECT extId, uuidStr FROM AtempDaily where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();
	
	@Modifying
	@Query("UPDATE AtempDaily SET needSync=false, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);
	
	@Modifying
	@Query("UPDATE AtempDaily SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync, @Param("extIdIn") String extIdIn);
	
	@Modifying
	@Query("UPDATE AtempDaily SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync, @Param("extIdIn") Set<String> extIdIn);
	
	
}
