package com.phincon.talents.gateways.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.AtempDaily;
import com.phincon.talents.gateways.repository.AtempDailyRepository;

@Service
public class AtempDailyService {
	
	@Autowired
	AtempDailyRepository atempDailyRepository;
	
	@Transactional
	public AtempDaily findAtempDaily(Long id){
		return atempDailyRepository.findOne(id);
	}
	
	@Transactional
	public AtempDaily findByExtId(String extId){
		return atempDailyRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<AtempDaily> findNeedSync(){
		return atempDailyRepository.findNeedSync();
	}
	
	@Transactional
	public Iterable<AtempDaily> findAll(){
		return atempDailyRepository.findAll();
	}
	
	@Transactional
	public void save(AtempDaily obj){
		atempDailyRepository.save(obj);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		atempDailyRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		atempDailyRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		atempDailyRepository.updateAckSyncStatus(status, extId);
	}
	
}
