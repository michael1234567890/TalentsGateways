package com.phincon.talents.gateways.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Pattern;
import com.phincon.talents.gateways.repository.PatternRepository;

@Service
public class PatternService {
	
	@Autowired
	PatternRepository patternRepository;
	
	@Transactional
	public Pattern findPattern(Long id){
		return patternRepository.findOne(id);
	}
	
	@Transactional
	public Pattern findByExtId(String extId){
		return patternRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<Pattern> findNeedSync(){
		return patternRepository.findNeedSync();
	}
	
	@Transactional
	public Iterable<Pattern> findAll(){
		return patternRepository.findAll();
	}
	
	@Transactional
	public void save(Pattern obj){
		patternRepository.save(obj);
	}
	
}
