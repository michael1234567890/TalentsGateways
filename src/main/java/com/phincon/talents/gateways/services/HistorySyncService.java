package com.phincon.talents.gateways.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.HistorySync;
import com.phincon.talents.gateways.repository.HistorySyncRepository;

@Service
public class HistorySyncService {
	
	@Autowired
	HistorySyncRepository historySyncRepository;
	
	@Transactional
	public HistorySync findById(Long id){
		return historySyncRepository.findOne(id);
	}
	
	@Transactional
	public HistorySync findByExtId(String extId){
		return historySyncRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<HistorySync> findAll(){
		return historySyncRepository.findAll();
	}
	
	@Transactional
	public List<HistorySync> findByModuleNameAndCompanyId(String module, Long companyId){
		return historySyncRepository.findByModuleNameAndCompanyId(module, companyId);
	}
	
//	@Transactional
//	public Iterable<HistorySync> findAllExtIdNull(){
//		return historySyncRepository.findAllExtIdNull();
//	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		historySyncRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid){
		historySyncRepository.updateExtIdByUUID(extId, uuid);
	}
}
