package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Pattern;

@Repository
public interface PatternRepository extends PagingAndSortingRepository<Pattern, Long>{
	
	@Query
	Pattern findByExtId(String extId);
	
	@Query("SELECT u FROM Pattern u WHERE u.needSync=true")
	List<Pattern> findNeedSync();
}
