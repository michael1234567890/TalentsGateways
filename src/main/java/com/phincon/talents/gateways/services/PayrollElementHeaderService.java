package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementHeader;
import com.phincon.talents.gateways.repository.PayrollElementHeaderRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementHeaderService {

	@Autowired
	PayrollElementHeaderRepository payrollElementHeaderRepository;

	
	
	@Transactional
	public Iterable<PayrollElementHeader> findAll() {
		return payrollElementHeaderRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementHeader obj) {
		payrollElementHeaderRepository.save(obj);
	}
	
	@Transactional
	public PayrollElementHeader findByExtId(String extId) {
		return payrollElementHeaderRepository.findByExtId(extId);
	}
	
}
