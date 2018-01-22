package com.phincon.talents.gateways.services;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.EmployeePayroll;
import com.phincon.talents.gateways.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService {
	
	@Autowired
	EmployeePayrollRepository employeePayrollRepository;
	
	@Transactional
	public EmployeePayroll findEmployeePayroll(Long id){
		return employeePayrollRepository.findOne(id);
	}
	
	@Transactional
	public EmployeePayroll findByExtId(String extId){
		return employeePayrollRepository.findByExtId(extId);
	}
	
	@Transactional
	public List<EmployeePayroll> findNeedSync(){
		return employeePayrollRepository.findNeedSync();
	}
	
	@Transactional
	public Iterable<EmployeePayroll> findAll(){
		return employeePayrollRepository.findAll();
	}
	
	@Transactional
	public void save(EmployeePayroll obj){
		employeePayrollRepository.save(obj);
	}
	
	@Transactional
	public void updateAckSyncStatus(boolean status, String extId) {
		employeePayrollRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		employeePayrollRepository.updateAckSyncStatus(status, extId);
	}
}
