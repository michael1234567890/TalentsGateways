package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.SyncSettings;
import com.phincon.talents.gateways.repository.SyncSettingsRepository;

@Service
public class SyncSettingsService {
	
	@Autowired
	SyncSettingsRepository syncSettingsRepository;
	
	@Transactional
	public SyncSettings findById(Long id){
		return syncSettingsRepository.findOne(id);
	}
	
	@Transactional
	public Iterable<SyncSettings> findAll(){
		return syncSettingsRepository.findAll();
	}
	
	@Transactional
	public SyncSettings findByModuleName(String module){
		return syncSettingsRepository.findByModuleName(module);
	}
}
