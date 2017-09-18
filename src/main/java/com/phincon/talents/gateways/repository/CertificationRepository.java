package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Certification;

@Repository
public interface CertificationRepository extends
		PagingAndSortingRepository<Certification, Long> {

	@Query
	Certification findByExtId(String extId);

	@Query("SELECT u FROM Certification u WHERE u.extId is NULL")
	List<Certification> findAllExtIdNull();

	@Modifying
	@Query("UPDATE Certification SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Certification SET needSync=false, ackSync=true, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,
			@Param("uuid") String uuid);

	@Query("SELECT u FROM Certification u WHERE u.needSync=true")
	List<Certification> findNeedSync();

	@Query("SELECT extId,uuidStr FROM Certification where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();

	@Modifying
	@Query("UPDATE Certification SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") Set<String> extIdIn);

	@Modifying
	@Query("UPDATE Certification SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") String extIdIn);

}
