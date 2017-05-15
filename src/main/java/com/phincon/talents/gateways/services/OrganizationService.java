package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Organization;
import com.phincon.talents.gateways.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Transactional
	public Organization findOrganization(Long id){
		return organizationRepository.findOne(id);
	}
	
	@Transactional
	public Organization findByExtId(String extId){
		return organizationRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Organization> findAll(){
		return organizationRepository.findAll();
	}
	
	@Transactional
	public Iterable<Organization> findAllExtIdNull(){
		return organizationRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		organizationRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid){
		organizationRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void save(Organization obj){
		organizationRepository.save(obj);
	}
	
}
