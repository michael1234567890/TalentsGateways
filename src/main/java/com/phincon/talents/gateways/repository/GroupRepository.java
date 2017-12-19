package com.phincon.talents.gateways.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Group;

@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group, Long>{
	
	@Query
	Group findByExtId(String extId);
	
	@Query("SELECT u FROM Group u WHERE u.needSync=true")
	List<Group> findNeedSync();
}
