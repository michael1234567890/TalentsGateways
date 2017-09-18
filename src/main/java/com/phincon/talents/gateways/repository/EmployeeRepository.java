package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Address;
import com.phincon.talents.gateways.model.Employee;

@Repository
public interface EmployeeRepository extends
		PagingAndSortingRepository<Employee, Long> {

	@Query
	Employee findByExtId(String extId);

	@Query("SELECT extId,uuidStr FROM Employee where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();

	@Query("SELECT u FROM Employee u WHERE u.extId is NULL")
	List<Employee> findAllExtIdNull();

	@Modifying
	@Query("UPDATE Employee SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);

	@Modifying
	@Query("UPDATE Employee SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") Set<String> extIdIn);

	@Modifying
	@Query("UPDATE Employee SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,
			@Param("extIdIn") String extIdIn);

	@Query("SELECT u FROM Employee u WHERE u.needSync=true")
	List<Employee> findNeedSync();

	@Modifying
	@Query("UPDATE Employee SET extId=:extId, ackSync=true, needSync=false WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,
			@Param("uuid") String uuid);

}
