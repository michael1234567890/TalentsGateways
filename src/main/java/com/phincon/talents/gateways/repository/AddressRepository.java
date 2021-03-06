package com.phincon.talents.gateways.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Address;

@Repository
public interface AddressRepository extends
		PagingAndSortingRepository<Address, Long> {

	@Query
	Address findByExtId(String extId);
	
	@Query("SELECT u FROM Address u WHERE u.extId is NULL")
	List<Address> findAllExtIdNull();

	@Modifying
	@Query("UPDATE Address SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
	
	@Query("SELECT extId,uuidStr FROM Address where ackSync=false AND extId != null")
	List<Object[]> findSendAckSync();


	@Modifying
	@Query("UPDATE Address SET ackSync=:ackSync WHERE extId IN :extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") Set<String> extIdIn);
	
	@Modifying
	@Query("UPDATE Address SET ackSync=:ackSync WHERE extId=:extIdIn")
	void updateAckSyncStatus(@Param("ackSync") boolean ackSync,@Param("extIdIn") String extIdIn);
	
	
	@Modifying
	@Query("UPDATE Address SET needSync=false,ackSync=true, extId=:extId WHERE uuidStr=:uuid")
	void updateExtIdByUUID(@Param("extId") String extId,@Param("uuid") String uuid);

	@Query("SELECT u FROM Address u WHERE u.needSync=true")
	List<Address> findNeedSync();
}
