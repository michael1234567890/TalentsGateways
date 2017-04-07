package com.phincon.talents.gateways.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.phincon.talents.gateways.model.ConnectedApp;

@Repository
public interface ConnectedAppRepository extends
		PagingAndSortingRepository<ConnectedApp, Long> {

	@Query
	ConnectedApp findByCompany(Long companyId);

}
