package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.TMBalance;

@Repository
public interface TMBalanceRepository extends
		PagingAndSortingRepository<TMBalance, Long> {

	@Query
	TMBalance findByExtId(String extId);

	@Query("SELECT u FROM TMBalance u WHERE u.needSync=true")
	List<TMBalance> findNeedSync();

	@Modifying
	@Query("UPDATE TMBalance SET needSync=false, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,
			@Param("uuid") String uuid);

	@Query("SELECT extId,uuidStr FROM TMBalance where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();

	@Modifying
	@Query("UPDATE TMBalance SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") Set<String> extIdIn);

	@Modifying
	@Query("UPDATE TMBalance SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") String extIdIn);

}
