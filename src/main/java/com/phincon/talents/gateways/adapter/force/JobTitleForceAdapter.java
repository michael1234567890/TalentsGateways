package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.JobTitle;
import com.phincon.talents.gateways.services.JobTitleService;
import com.phincon.talents.gateways.utils.Utils;

@Service
public class JobTitleForceAdapter extends ForceAdapter<JobTitle> {

	@Autowired
	JobTitleService jobTitleService;
	
	
	public JobTitleForceAdapter() {
		super();
		
		query = "WSSETJOBTITLE";
	}
	
	@Override
	protected JobTitle convertMapResultToObject(Map<String, Object> mapResult){
		String extId = (String) mapResult.get("Id");
		Date endDate = null;
		String strEndDate = (String) mapResult.get("End_Date__c");
		if(strEndDate != null)
			endDate = Utils.convertStringToDate(strEndDate);
		
		Date startDate = null;
		String strStartDate = (String) mapResult.get("Start_Date__c");
		if(strStartDate != null)
			startDate = Utils.convertStringToDate(strStartDate);
		
		String description = (String)mapResult.get("Job_Title_Description__c");
		String remark = (String)mapResult.get("Remark__c");
		
		Integer totalBudget = (Integer)mapResult.get("Job_Title_Total_Budget__c");
//		Double totalBudget = 0.00;
//		if(strTotalBudget != null)
//			totalBudget = Double.valueOf(strTotalBudget);
		String jobTitleType = (String)mapResult.get("Job_Title_Type__c");
		Integer totalNumber = (Integer)mapResult.get("Total_Number__c");
		
		String name = (String) mapResult.get("Name");
		
		JobTitle jobtitle = new JobTitle();
		jobtitle.setDescription(description);
		jobtitle.setEndDate(endDate);
		jobtitle.setExtId(extId);
		jobtitle.setName(name);
		jobtitle.setRemark(remark);
		jobtitle.setStartDate(startDate);
		jobtitle.setTotalBudget(totalBudget);
		jobtitle.setTotalNumber(totalNumber);
		jobtitle.setType(jobTitleType);
		
		return jobtitle;
	}
	
	@Override
	public void saveListData(List<JobTitle> listData){
		for(JobTitle e : listData){
			JobTitle jobTitle = jobTitleService.findByExtId(e.getExtId());
			
			if(jobTitle == null){
				jobTitle = new JobTitle();
				jobTitle.setCreatedDate(new Date());
			}
			jobTitle.setExtId(e.getExtId());
			jobTitle.setCompany(this.companyid);
			jobTitle.setEndDate(e.getEndDate());
			jobTitle.setStartDate(e.getStartDate());
			jobTitle.setExtId(e.getExtId());
			jobTitle.setName(e.getName());
			jobTitle.setRemark(e.getRemark());
			jobTitle.setTotalBudget(e.getTotalBudget());
			jobTitle.setTotalNumber(e.getTotalNumber());
			jobTitle.setType(e.getType());
			jobTitle.setDescription(e.getDescription());
			jobTitle.setModifiedDate(new Date());
			jobTitle.setModifiedBy("Talents Gateway");
			jobTitle.setCreatedBy("Talents Gateway");
			jobTitleService.save(jobTitle);
			System.out.println("Success Save Job Title");
		}
	}
	
}
