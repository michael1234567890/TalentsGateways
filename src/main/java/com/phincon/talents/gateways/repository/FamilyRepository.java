package com.phincon.talents.gateways.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;

@Repository
public interface FamilyRepository extends PagingAndSortingRepository<Family,Long>{
	
	
	 @Query
	 Family findByExtId(String extId);
	 
	  @Query("SELECT u FROM Family u WHERE u.extId is NULL")
	 List<Family> findAllExtIdNull();
	  
	  @Query("SELECT u FROM Family u WHERE u.extId is not NULL")
	  List<Family> getAllExtIdNotNull();
	  
	  @Query("SELECT u FROM Family u WHERE u.modifiedDate >= :lastSync")
	  List<Family> getAllDataForUpdate(@Param("lastSync") Date lastSync);
	  
	  @Modifying
	  @Query("UPDATE Family SET extId=:extId WHERE id=:id")
	  void updateExtIdById(@Param("extId") String extId,@Param("id")  Long id);
	  
	  @Modifying
	  @Query("UPDATE Family SET needSync=false, extId=:extId WHERE uuidStr=:uuid")
	  void updateExtIdByUUID(@Param("extId") String extId,@Param("uuid")  String uuid);
		
	  @Query("SELECT u FROM Family u WHERE u.needSync=true")
	  List<Family> findNeedSync();
	  
	 
	
}
