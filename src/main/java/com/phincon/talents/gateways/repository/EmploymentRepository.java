package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Employment;

@Repository
public interface EmploymentRepository extends
		PagingAndSortingRepository<Employment, Long> {

	@Query
	Employment findByExtId(String extId);

	@Query("SELECT u FROM Employment u WHERE u.extId is NULL")
	List<Employment> findAllExtIdNull();

	@Modifying
	@Query("UPDATE Employment SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Employment SET ackSync=true, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,
			@Param("uuid") String uuid);

	@Query("SELECT extId,uuidStr FROM Employment where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();

	@Modifying
	@Query("UPDATE Employment SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") Set<String> extIdIn);

	@Modifying
	@Query("UPDATE Employment SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") String extIdIn);



	//user provisioning
	@Query("SELECT u FROM Employment u WHERE employee_id = :employeeID")
	List<Employment> findByEmployee(@Param("employeeID") Long employeeID);

	

}
