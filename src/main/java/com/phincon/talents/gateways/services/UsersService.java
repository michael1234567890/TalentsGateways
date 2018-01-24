package com.phincon.talents.gateways.services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Users;
import com.phincon.talents.gateways.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	UsersRepository userRepository;

	
	@Transactional
	public Iterable<Users> getAllUsers(){
		return userRepository.getAllUsers();
	}

	@Transactional
	public void save(Users obj) {
		userRepository.save(obj);
	}

}
