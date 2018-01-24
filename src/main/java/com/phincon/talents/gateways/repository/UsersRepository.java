package com.phincon.talents.gateways.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.phincon.talents.gateways.model.Users;

@Repository
public interface  UsersRepository extends PagingAndSortingRepository<Users, Long> {

	
	
	@Query("SELECT u FROM Users u ")
	List<Users> getAllUsers();
	
	
}
