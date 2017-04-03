package com.phincon.talents.gateways;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.services.EmployeeService;

@Service
public class EmployeeForceAdapter extends ForceAdapter<Employee> {

	@Autowired
	EmployeeService employeeService;
	
	public EmployeeForceAdapter() {
		super();
		query = "select id, First_Name__c from HRPERINFO__c limit 10";
	}

	@Override
	protected Employee convertMapResultToObject(Map<String, Object> mapResult) {
		String firstName = (String) mapResult.get("First_Name__c");
		String extId = (String) mapResult.get("Id");
		Employee employee = new Employee();
		employee.setExtId(extId);
		employee.setFirstName(firstName);
		return employee;
	}

	@Override
	public void saveListDate(List<Employee> listData) {
		for (Employee e : listData) {
			System.out.print("Employee : " + e.getExtId());
			// check is id is exist
			Employee empDb = employeeService.findByExtId(e.getExtId());

			// if exist doing update
			if (empDb == null) {
				empDb = new Employee();
			}
			empDb.setExtId(e.getExtId());
			empDb.setFirstName(e.getFirstName());

			employeeService.save(empDb);
			System.out.println("Success Save Employee");
		}
	}

}
