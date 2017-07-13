package com.phincon.talents.gateways.repository;

import java.util.List;

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
	void updateExtIdByUUID(@Param("extId") String extId,@Param("uuid")  String uuid);
}
