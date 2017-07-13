package com.phincon.talents.gateways.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.model.TMRequest;
import com.phincon.talents.gateways.repository.TMBalanceRepository;
import com.phincon.talents.gateways.repository.TMRequestRepository;

@Service
public class TMRequestService {
	
	@Autowired
	TMRequestRepository tmRequestRepository;

	@Autowired
	TMBalanceRepository tmBalanceRepository;
	
	
	
	@Transactional
	public TMRequest findById(Long id) {
		return tmRequestRepository.findOne(id);
	}
	
	@Transactional
	public TMRequest findByExtId(String extId) {
		return tmRequestRepository.findByExtId(extId);
	}
	
	
	@Transactional
	public void save(TMRequest obj) {
		tmRequestRepository.save(obj);
	}
	
	@Transactional
	public List<TMRequest> findNeedSync() {
		return tmRequestRepository.findNeedSync();
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		tmRequestRepository.updateExtIdByUUID(extId, uuid);
	}
	

	
}
