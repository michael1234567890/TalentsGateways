package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.SyncSettings;

@Repository
public interface SyncSettingsRepository extends PagingAndSortingRepository<SyncSettings, Long>{
	
	@Query
	SyncSettings findByModuleName(String module);
}
