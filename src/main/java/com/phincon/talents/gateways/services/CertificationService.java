package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Certification;
import com.phincon.talents.gateways.repository.CertificationRepository;

@Service
public class CertificationService {
	@Autowired
	CertificationRepository certificationRepository;
	
	@Transactional
	public Certification findById(Long id){
		return certificationRepository.findOne(id);
	}
	
	@Transactional
	public Certification findByExtId(String extId){
		return certificationRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Certification> findAll(){
		return certificationRepository.findAll();
	}
	
	@Transactional
	public Iterable<Certification> findAllExtIdNull(){
		return certificationRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		certificationRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void save(Certification obj){
		certificationRepository.save(obj);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid){
		certificationRepository.updateExtIdByUUID(extId, uuid);
	}
}
