package com.phincon.talents.gateways.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.repository.FamilyRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class FamilyService {

	@Autowired
	FamilyRepository familyRepository;

	@Transactional
	public Family findById(Long id) {
		return familyRepository.findOne(id);
	}

	@Transactional
	public Family findByExtId(String extId) {
		return familyRepository.findByExtId(extId);
	}

	@Transactional
	public Iterable<Family> findAll() {
		return familyRepository.findAll();
	}
	
	@Transactional
	public Iterable<Family> findAllExtIdNull() {
		return familyRepository.findAllExtIdNull();
	}
	
	@Transactional
	public Iterable<Family> getAllExtIdNotNull(){
		return familyRepository.getAllExtIdNotNull();
	}
	
	@Transactional
	public Iterable<Family> getAllDataForUpdate(Date lastSync){
		return familyRepository.getAllDataForUpdate(lastSync);
	}

	@Transactional
	public void save(Family obj) {
		familyRepository.save(obj);
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id) {
		familyRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		familyRepository.updateExtIdByUUID(extId, uuid);
	}
	

	@Transactional
	public List<Family> findNeedSync() {
		return familyRepository.findNeedSync();
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		familyRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		familyRepository.updateAckSyncStatus(status, extId);
	}
	


}
