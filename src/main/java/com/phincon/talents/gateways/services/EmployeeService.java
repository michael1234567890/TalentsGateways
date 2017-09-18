package com.phincon.talents.gateways.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.repository.EmployeeRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee findEmployee(Long id) {
		return employeeRepository.findOne(id);
	}
	
	
	@Transactional
	public Employee findByExtId(String extId) {
		return employeeRepository.findByExtId(extId);
	}
	
	
	@Transactional
	public Iterable<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	@Transactional
	public List<Employee> findNeedSync() {
		return employeeRepository.findNeedSync();
	}

	@Transactional
	public void save(Employee obj) {
		employeeRepository.save(obj);
	}
	
	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		employeeRepository.updateExtIdByUUID(extId, uuid);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		employeeRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		employeeRepository.updateAckSyncStatus(status, extId);
	}



}
