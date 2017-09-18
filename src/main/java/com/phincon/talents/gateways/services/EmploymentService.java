package com.phincon.talents.gateways.services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employment;
import com.phincon.talents.gateways.repository.EmploymentRepository;

@Service
public class EmploymentService {
	@Autowired
	EmploymentRepository employmentRepository;
	
	@Transactional
	public Employment findEmployment(Long id){
		return employmentRepository.findOne(id);
	}
	
	@Transactional
	public Employment findByExtId(String extId){
		return employmentRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Employment> findAll(){
		return employmentRepository.findAll();
	}
	
	@Transactional
	public Iterable<Employment> findAllExtIdNull(){
		return employmentRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		employmentRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void save(Employment obj){
		employmentRepository.save(obj);
	}
	

	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		employmentRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		employmentRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		employmentRepository.updateAckSyncStatus(status, extId);
	}
}
