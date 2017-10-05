package com.phincon.talents.gateways.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Shift;
import com.phincon.talents.gateways.repository.ShiftRepository;

@Service
public class ShiftService {
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Transactional
	public Shift findShift(Long id){
		return shiftRepository.findOne(id);
	}
	
	@Transactional
	public Shift findByExtId(String extId){
		return shiftRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<Shift> findNeedSync(){
		return shiftRepository.findNeedSync();
	}
	
	@Transactional
	public Iterable<Shift> findAll(){
		return shiftRepository.findAll();
	}
	
	@Transactional
	public void save(Shift obj){
		shiftRepository.save(obj);
	}
}
