package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.ConnectedApp;
import com.phincon.talents.gateways.repository.ConnectedAppRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class ConnectedAppService {

	@Autowired
	ConnectedAppRepository connectedAppRepository;

	@Transactional
	public ConnectedApp findByCompany(Long companyId) {
		return connectedAppRepository.findByCompany(companyId);
	}
	
	
	@Transactional
	public void save(ConnectedApp obj) {
		connectedAppRepository.save(obj);
	}



}
