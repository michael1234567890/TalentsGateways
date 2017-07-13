package com.phincon.talents.gateways.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementDetailYearly;
import com.phincon.talents.gateways.repository.PayrollElementDetailYearlyRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementDetailYearlyService {

	@Autowired
	PayrollElementDetailYearlyRepository payrollElementDetailYearlyRepository;

	@Transactional
	public Iterable<PayrollElementDetailYearly> findAll() {
		return payrollElementDetailYearlyRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementDetailYearly obj) {
		payrollElementDetailYearlyRepository.save(obj);
	}
	
	@Transactional
	public PayrollElementDetailYearly findByExtId(String extId) {
		return payrollElementDetailYearlyRepository.findByExtId(extId);
	}
	
}
