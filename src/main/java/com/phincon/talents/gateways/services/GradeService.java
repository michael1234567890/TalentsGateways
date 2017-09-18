package com.phincon.talents.gateways.services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Grade;
import com.phincon.talents.gateways.repository.GradeRepository;

@Service
public class GradeService {
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Transactional
	public Grade findPosition(Long id){
		return gradeRepository.findOne(id);
	}
	
	@Transactional
	public Grade findByExtId(String extId){
		return gradeRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Grade> findAll(){
		return gradeRepository.findAll();
	}
	
	@Transactional
	public Iterable<Grade> findAllExtIdNull(){
		return gradeRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		gradeRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid){
		gradeRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void save(Grade obj){
		gradeRepository.save(obj);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		gradeRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		gradeRepository.updateAckSyncStatus(status, extId);
	}
	
}
