package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{
	
	
	 @Query
	 Employee findByExtId(String extId);
	 
	 @Query("SELECT u FROM Employee u WHERE u.needSync=true")
	  List<Employee> findNeedSync();
	  
	  @Modifying
	  @Query("UPDATE Employee SET extId=:extId, needSync=false WHERE uuidStr=:uuid")
	  void updateExtIdByUUID(@Param("extId") String extId,@Param("uuid")  String uuid);
		
	
}
