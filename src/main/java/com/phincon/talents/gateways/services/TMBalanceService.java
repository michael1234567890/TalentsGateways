package com.phincon.talents.gateways.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.TMBalance;
import com.phincon.talents.gateways.repository.TMBalanceRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class TMBalanceService {

	@Autowired
	TMBalanceRepository tmBalanceRepository;

	
	
	@Transactional
	public Iterable<TMBalance> findAll() {
		return tmBalanceRepository.findAll();
	}

	@Transactional
	public void save(TMBalance obj) {
		tmBalanceRepository.save(obj);
	}
	
	@Transactional
	public TMBalance findByExtId(String extId) {
		return tmBalanceRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<TMBalance> findNeedSync() {
		return tmBalanceRepository.findNeedSync();
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		tmBalanceRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		tmBalanceRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		tmBalanceRepository.updateAckSyncStatus(status, extId);
	}
	
	
}
