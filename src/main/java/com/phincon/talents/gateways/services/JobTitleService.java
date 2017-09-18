package com.phincon.talents.gateways.services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.JobTitle;
import com.phincon.talents.gateways.repository.JobTitleRepository;

@Service
public class JobTitleService {
	@Autowired
	JobTitleRepository jobTitleRepository;
	
	@Transactional
	public JobTitle findJobTitle(Long id){
		return jobTitleRepository.findOne(id);
	}
	
	@Transactional
	public JobTitle findByExtId(String extId){
		return jobTitleRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<JobTitle> findAll(){
		return jobTitleRepository.findAll();
	}
	
	@Transactional
	public Iterable<JobTitle> findAllExtIdNull(){
		return jobTitleRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		jobTitleRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void save(JobTitle obj){
		jobTitleRepository.save(obj);
	}
	

	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		jobTitleRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		jobTitleRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		jobTitleRepository.updateAckSyncStatus(status, extId);
	}
}
