package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementHeaderYearly;
import com.phincon.talents.gateways.repository.PayrollElementHeaderYearlyRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementHeaderYearlyService {

	@Autowired
	PayrollElementHeaderYearlyRepository payrollElementHeaderRepository;

	
	
	@Transactional
	public Iterable<PayrollElementHeaderYearly> findAll() {
		return payrollElementHeaderRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementHeaderYearly obj) {
		payrollElementHeaderRepository.save(obj);
	}
	
	@Transactional
	public PayrollElementHeaderYearly findByExtId(String extId) {
		return payrollElementHeaderRepository.findByExtId(extId);
	}
	
}
