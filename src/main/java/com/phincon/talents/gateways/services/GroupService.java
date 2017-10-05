package com.phincon.talents.gateways.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Group;
import com.phincon.talents.gateways.repository.GroupRepository;

@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	@Transactional
	public Group findGroup(Long id){
		return groupRepository.findOne(id);
	}
	
	@Transactional
	public Group findByExtId(String extId){
		return groupRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<Group> findNeedSync(){
		return groupRepository.findNeedSync();
	}
	
	@Transactional
	public Iterable<Group> findAll(){
		return groupRepository.findAll();
	}
	
	@Transactional
	public void save(Group obj){
		groupRepository.save(obj);
	}
	
}
