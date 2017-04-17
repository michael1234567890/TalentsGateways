package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phincon.talents.gateways.model.Certification;

@Repository
public interface CertificationRepository extends PagingAndSortingRepository<Certification, Long> {
	
	@Query
	Certification findByExtId(String extId);
	
	@Query("SELECT u FROM Certification u WHERE u.extId is NULL")
	List<Certification> findAllExtIdNull();
	
	@Modifying
	@Query("UPDATE Certification SET extId=:extId WHERE id=:id")
	void updateExtIdById(@Param("extId") String extId, @Param("id") Long id);
}
