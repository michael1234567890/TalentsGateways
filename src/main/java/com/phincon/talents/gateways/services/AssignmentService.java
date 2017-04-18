package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Assignment;
import com.phincon.talents.gateways.repository.AssignmentRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AssignmentService {

	@Autowired
	AssignmentRepository assignmentRepository;

	@Transactional
	public Assignment findById(Long id) {
		return assignmentRepository.findOne(id);
	}

	@Transactional
	public Assignment findByExtId(String extId) {
		return assignmentRepository.findByExtId(extId);
	}

	@Transactional
	public Iterable<Assignment> findAll() {
		return assignmentRepository.findAll();
	}
	
	@Transactional
	public Iterable<Assignment> findAllExtIdNull() {
		return assignmentRepository.findAllExtIdNull();
	}

	@Transactional
	public void save(Assignment obj) {
		assignmentRepository.save(obj);
	}
	
	@Transactional
	public void updateExtIdById(String extId, Long id) {
		assignmentRepository.updateExtIdById(extId, id);
	}

}
