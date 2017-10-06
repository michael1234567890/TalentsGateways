package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.EmployeePayroll;

@Repository
public interface EmployeePayrollRepository extends PagingAndSortingRepository<EmployeePayroll, Long>{

	@Query
	EmployeePayroll findByExtId(String extId);
	
	@Query("SELECT u FROM EmployeePayroll u WHERE u.extId is NULL")
	List<EmployeePayroll> findAllExtIdNull();
	
	@Query("SELECT u FROM EmployeePayroll u WHERE u.needSync=true")
	List<EmployeePayroll> findNeedSync();
	
	@Modifying
	@Query("UPDATE EmployeePayroll SET extId = :extId WHERE id = :id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Modifying
	@Query("UPDATE EmployeePayroll SET extId = :extId, ackSync = true WHERE uuidStr = :uuid")
	void updateExtIdByUUID(@Param("extId") String extId, @Param("uuid") String uuid);
	
	@Query("SELECT extId, uuidStr FROM EmployeePayroll WHERE ackSync = false AND extId != null")
	List<Object[]> findSendAckSync();
	
	@Modifying
	@Query("UPDATE EmployeePayroll SET ackSync = :ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync, @Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE EmployeePayroll SET ackSync = :ackSync WHERE extId = :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync, @Param("extIdIn") String extIdIn);
}
