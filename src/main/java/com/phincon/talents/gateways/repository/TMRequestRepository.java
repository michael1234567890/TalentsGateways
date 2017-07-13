package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.TMRequest;

@Repository
public interface TMRequestRepository extends
		PagingAndSortingRepository<TMRequest, Long> {

	@Query
	List<TMRequest> findByTmRequestHeader(Long tmRequestHeader);

	@Query
	TMRequest findByExtId(String extId);

	@Query("SELECT u FROM TMRequest u WHERE u.needSync=true")
	List<TMRequest> findNeedSync();
	
	@Modifying
	@Query("UPDATE TMRequest SET needSync=false, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,@Param("uuid")  String uuid);

}
