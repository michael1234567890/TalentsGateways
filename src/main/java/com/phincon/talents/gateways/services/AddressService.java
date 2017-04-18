package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Address;
import com.phincon.talents.gateways.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	
	@Transactional
	public Address findAddress(Long id){
		return addressRepository.findOne(id);
	}
	
	@Transactional
	public Address findByExtId(String extId){
		return addressRepository.findByExtId(extId);
	}
	
	@Transactional
	public Iterable<Address> findAll(){
		return addressRepository.findAll();
	}
	
	@Transactional
	public Iterable<Address> findAllExtIdNull(){
		return addressRepository.findAllExtIdNull();
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id){
		addressRepository.updateExtIdById(extId, id);
	}
	
	@Transactional
	public void save(Address obj){
		addressRepository.save(obj);
	}
	

	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		addressRepository.updateExtIdByUUID(extId, uuid);
	}
}
