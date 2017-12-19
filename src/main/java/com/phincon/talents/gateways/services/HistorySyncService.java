package com.phincon.talents.gateways.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.HistorySync;
import com.phincon.talents.gateways.repository.HistorySyncRepository;

@Service
public class HistorySyncService {

	@Autowired
	HistorySyncRepository historySyncRepository;

	@Transactional
	public HistorySync findById(Long id) {
		return historySyncRepository.findOne(id);
	}

	@Transactional
	public HistorySync findByExtId(String extId) {
		return historySyncRepository.findByExtId(extId);
	}

	@Transactional
	public Iterable<HistorySync> findAll() {
		return historySyncRepository.findAll();
	}

	@Transactional
	public List<HistorySync> findByModuleNameAndCompanyId(String module,
			Long companyId) {
		return historySyncRepository.findByModuleNameAndCompanyId(module,
				companyId);
	}

	// @Transactional
	// public Iterable<HistorySync> findAllExtIdNull(){
	// return historySyncRepository.findAllExtIdNull();
	// }

	@Transactional
	public void updateExtIdById(String extId, Long id) {
		historySyncRepository.updateExtIdById(extId, id);
	}

	@Transactional
	public void updateExtIdByUUID(String extId, String uuid) {
		historySyncRepository.updateExtIdByUUID(extId, uuid);
	}

	@Transactional
	public void save(HistorySync obj) {
		historySyncRepository.save(obj);
	}

	@Transactional
	public void createOrUpdateSync(String moduleName, Long companyId, Date startSync) {
		List<HistorySync> historySync = findByModuleNameAndCompanyId(
				moduleName, companyId);
		HistorySync historySyncObj = null;
		if (historySync != null && historySync.size() > 0) {
			historySyncObj = historySync.get(0);
			historySyncObj.setModifiedBy("talent gateway");
			historySyncObj.setModifiedDate(new Date());
			Date nextSyncTime = new Date();
			if (historySyncObj.getDiffNextSync() == 0) {
				nextSyncTime.setTime(historySyncObj.getLastSync().getTime()
						+ (int) (HistorySync.DIFF_SYNC * 1000));
			} else {
				nextSyncTime.setTime(historySyncObj.getLastSync().getTime()
						+ (int) (historySyncObj.getDiffNextSync() * 1000));
			}
			historySyncObj.setStartSync(startSync);
			historySyncObj.setEndSync(new Date());
			historySyncObj.setNextSyncTime(nextSyncTime);

		} else {
			historySyncObj = new HistorySync();
			historySyncObj.setCompanyId(companyId);
			historySyncObj.setCreatedBy("talent gateway");
			historySyncObj.setCreatedDate(new Date());
			historySyncObj.setLastSync(new Date());
			historySyncObj.setDiffNextSync(HistorySync.DIFF_SYNC);
			historySyncObj.setModifiedDate(new Date());
			historySyncObj.setModuleName(moduleName);
			historySyncObj.setModifiedBy("talent gateway");
			historySyncObj.setStartSync(startSync);
			historySyncObj.setEndSync(new Date());
			Date nextSyncTime = new Date();
			nextSyncTime.setTime(historySyncObj.getLastSync().getTime()
					+ (int) (HistorySync.DIFF_SYNC * 1000));
			historySyncObj.setNextSyncTime(nextSyncTime);
		}
		save(historySyncObj);
	}
}
