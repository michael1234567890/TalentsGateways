package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Organization;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Long>{
	@Query
	Organization findByExtId(String extId);
	
	@Query("SELECT u FROM Organization u WHERE u.extId is NULL")
	List<Organization> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Organization SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE Organization SET extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);

	@Query("SELECT extId,uuidStr FROM Organization where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();
	
	@Modifying
	@Query("UPDATE Organization SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE Organization SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") String extIdIn);

}
