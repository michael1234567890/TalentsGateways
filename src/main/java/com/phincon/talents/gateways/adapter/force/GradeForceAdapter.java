package com.phincon.talents.gateways.adapter.force;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Grade;
import com.phincon.talents.gateways.repository.GradeRepository;

@Service
public class GradeForceAdapter extends ForceAdapter<Grade> {

	@Autowired
	GradeRepository gradeRepository;
	

	
	
	public GradeForceAdapter() {
		super();
	}
	
	@Override
	protected Grade convertMapResultToObject(Map<String, Object> mapResult){
		
		
		String name = (String) mapResult.get("Name");
		String extId = (String) mapResult.get("Id");
		Grade obj = new Grade();
		obj.setExtId(extId);
		obj.setName(name);
		
		return obj;
	}
	
	@Override
	public void saveListData(List<Grade> listData){
		for(Grade e : listData){
			System.out.println("After Ext Id " + e.getExtId());
			Grade grade = gradeRepository.findByExtId(e.getExtId());
			
			if(grade == null){
				grade = new Grade() ;
				grade.setCreatedDate(new Date());
				grade.setCreatedBy("Talents Gateway");
				
			}
			grade.setExtId(e.getExtId());
			grade.setCompany(this.companyid);
			grade.setName(e.getName());
			gradeRepository.save(grade);
			System.out.println("Grade Has been saved");
		}
	}
	
}
