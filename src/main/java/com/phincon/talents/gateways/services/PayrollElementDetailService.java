package com.phincon.talents.gateways.services;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.PayrollElementDetail;
import com.phincon.talents.gateways.repository.PayrollElementDetailRepository;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementDetailService {

	@Autowired
	PayrollElementDetailRepository payrollElementDetailRepository;

	
	
	@Transactional
	public Iterable<PayrollElementDetail> findAll() {
		return payrollElementDetailRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementDetail obj) {
		payrollElementDetailRepository.save(obj);
	}
	
	@Transactional
	public PayrollElementDetail findByExtId(String extId) {
		return payrollElementDetailRepository.findByExtId(extId);
	}
	
	public void updateAckSyncStatus(boolean status, String extId) {
		payrollElementDetailRepository.updateAckSyncStatus(status, extId);
	}
	

	@Transactional
	public void updateAckSyncStatus(boolean status, Set<String> extId) {
		payrollElementDetailRepository.updateAckSyncStatus(status, extId);
	}
	
}
