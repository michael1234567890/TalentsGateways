package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import com.phincon.talents.gateways.model.Employee;
import com.phincon.talents.gateways.model.Family;
import com.phincon.talents.gateways.services.EmployeeService;
import com.phincon.talents.gateways.utils.ForceResponseGetId;
import com.phincon.talents.gateways.utils.Utils;


@Service
public class EmployeeForceAdapter extends ForceAdapter<Employee> {

	@Autowired
	EmployeeService employeeService;

	public EmployeeForceAdapter() {
		super();
//		query = "select Id,Employee_No__c, First_Name__c,Office_Email__c, Last_Name__c, Date_of_Birth__c,Birthplace__c,Mobile_Phone__c,Middle_Name__c,Religion__c, "
//				+ "Blood_Type__c,NRIC_No__c ,NRIC_Date__c,NRIC_Expiration_Date__c, No_NPWP__c,"
//				+ "NPWP_Date__c,Nationality__c,Emergency_Contact_Address__c,Emergency_Contact_Name__c,Emergency_Contact_Phone__c,Emergency_Contact_Other_Phone__c,Emergency_Contact_Relationship__c from HRPERINFO__c limit 10";
		query = "GetAllHRPERINFO";
	}

	@Override
	protected Employee convertMapResultToObject(Map<String, Object> mapResult) {
		String name = (String) mapResult.get("Name");
		String firstName = (String) mapResult.get("First_Name__c");
		String extId = (String) mapResult.get("Id");
		String officeMail = (String) mapResult.get("Office_Email__c");
		String lastName = (String) mapResult.get("Last_Name__c");
		Date dateBirth = null;

		if (mapResult.get("Date_of_Birth__c") != null)
			dateBirth = Utils.convertStringToDate((String) mapResult
					.get("Date_of_Birth__c"));

		String birthPlace = (String) mapResult.get("Birthplace__c");
		String mobilePhone = (String) mapResult.get("Mobile_Phone__c");
		String middleName = (String) mapResult.get("Middle_Name__c");
		String religion = (String) mapResult.get("Religion__c");
		String employeeNo = (String) mapResult.get("Employee_No__c");
		String bloodType = (String) mapResult.get("Blood_Type__c");
		
		String age = (String) mapResult.get("Age__c");
		String strDateAnniversary = (String) mapResult.get("Anniversary_Date__c");
		Date dateAnniversary = null;
		if(strDateAnniversary != null)
			dateAnniversary = Utils.convertStringToDate(strDateAnniversary);
		String applicantNo = (String) mapResult.get("Applicant_No__c");
		String attendanceCode = (String) mapResult.get("Attendance_Code_No__c");
		String authorized = (String) mapResult.get("Authorised__c");
		String bpjsKesehatan = (String) mapResult.get("BPJS_Kesehatan__c");
		String strDateBpjsKesehatan = (String) mapResult.get("BPJS_Kesehatan_Date__c");
		Date dateBpjsKesehatan = null;
		if(strDateBpjsKesehatan != null)
			dateBpjsKesehatan = Utils.convertStringToDate(strDateBpjsKesehatan);
		String bpjsKerja = (String) mapResult.get("BPJS_Tenaga_Kerja__c");
		String strDateBpjsKerja = (String) mapResult.get("BPJS_Tenaga_Kerja_Date__c");
		Date dateBpjsKerja = null;
		if(strDateBpjsKerja != null)
			dateBpjsKerja = Utils.convertStringToDate(strDateBpjsKerja);
		String clothSize = (String) mapResult.get("Cloth_Size__c");
		//String companyName = (String) mapResult.get("Company_Name__c");
		String dialect = (String) mapResult.get("Dialect__c");
		String disability = (String) mapResult.get("Disability__c");
		String strLicenseDrivingDate1 = (String) mapResult.get("Driving1_Date__c");
		Date licenseDrivingDate1 = null;
		if(strLicenseDrivingDate1 != null)
			licenseDrivingDate1 = Utils.convertStringToDate(strLicenseDrivingDate1);
		String strLicenseExpiredDriving1 = (String) mapResult.get("Driving1_Expiry_Date__c");
		Date licenseExpiredDriving1 = null;
		if(strLicenseExpiredDriving1 != null)
			licenseExpiredDriving1 = Utils.convertStringToDate(strLicenseExpiredDriving1);
		String licenseDrivingNo1 = (String) mapResult.get("Driving1_No__c");
		String strLicenseDrivingDate2 = (String) mapResult.get("Driving2_Date__c");
		Date licenseDrivingDate2 = null;
		if(strLicenseDrivingDate2 != null)
			licenseDrivingDate2 = Utils.convertStringToDate(strLicenseDrivingDate2);
		String strLicenseExpiredDriving2 = (String) mapResult.get("Driving2_Expiry_Date__c");
		Date licenseExpiredDriving2 = null;
		if(strLicenseExpiredDriving2 != null)
			licenseExpiredDriving2 = Utils.convertStringToDate(strLicenseExpiredDriving2);
		String licenseDrivingNo2 = (String) mapResult.get("Driving2_No__c");
		String educationTitle1 = (String) mapResult.get("Education_Title1__c");
		String educationTitle2 = (String) mapResult.get("Education_Title2__c");
		String emergencyContactAddress = (String) mapResult.get("Emergency_Contact_Address__c");
		String emergencyContactCity = (String) mapResult.get("Emergency_Contact_City__c");
		String emergencyContactCountry = (String) mapResult.get("Emergency_Contact_Country__c");
		String emergencyContactDistrict = (String) mapResult.get("Emergency_Contact_District__c");
		String emergencyContactName = (String) mapResult.get("Emergency_Contact_Name__c");
		String emergencyContactPhone = (String) mapResult.get("Emergency_Contact_Phone__c");
		String emergencyContactOtherPhone = (String) mapResult.get("Emergency_Contact_Other_Phone__c");
		String emergencyContactRelationship = (String) mapResult.get("Emergency_Contact_Relationship__c");
		String emergencyContactRt = (String) mapResult.get("Emergency_Contact_RT__c");
		String emergencyContactRw = (String) mapResult.get("Emergency_Contact_RW__c");
		String emergencyContactStateProvince = (String) mapResult.get("Emergency_Contact_State_Province__c");
		String emergencyContactSubdistrict = (String) mapResult.get("Emergency_Contact_Subdistrict__c");
		String emergencyContactZipCode = (String) mapResult.get("Emergency_Contact_ZIP_Code__c");
		String employeeStatus = (String) mapResult.get("Employee_Status__c");
		//String externalId = (String) mapResult.get("ExtId__c");
		//String facility = (String) mapResult.get("Facility__c");
		String familyCardNo = (String) mapResult.get("Family_Card_No__c");
		String fktpName = (String) mapResult.get("FKTP_Name__c");
//		String strForeignLanguage = (String) mapResult.get("Foreign_Language_Proficiency__c");
//		Date foreignLanguage = null;
//		if(strForeignLanguage != null)
//			foreignLanguage = Utils.convertStringToDate(strForeignLanguage);
		//Integer pensionReminderDay = (Integer) mapResult.get("Formula_Pension_Reminder_Days__c");
//		Date pensionReminderDay = null;
//		if(strPensionReminderDay != null)
//			pensionReminderDay = Utils.convertStringToDate(strPensionReminderDay);
		String strPensionReminderMonths = (String) mapResult.get("Formula_Pension_Reminder_Months__c");
		Date pensionReminderMonths = null;
		if(strPensionReminderMonths != null)
			pensionReminderMonths = Utils.convertStringToDate(strPensionReminderMonths);
//		String strPensionReminderYear = (String) mapResult.get("Formula_Pension_Reminder_Years__c");
//		Date pensionReminderYear = null;
//		if(strPensionReminderYear != null){
//			pensionReminderYear = Utils.convertStringToDate(strPensionReminderYear);
//		}
		String fullNameTitle = (String) mapResult.get("Full_Name_Title__c");
		String gender = (String) mapResult.get("Gender__c");
		String headSize = (String) mapResult.get("Head_Size__c");
		String height = (String) mapResult.get("Height__c");
		String strDateHire = (String) mapResult.get("Hire_Date__c");
		Date dateHire = null;
		if(strDateHire != null)
			dateHire = Utils.convertStringToDate(strDateHire);
		String hobby = (String) mapResult.get("Hobby__c");
		String initialName = (String) mapResult.get("Initial_Name__c");
		String interest = (String) mapResult.get("Interest__c");
		//String isUpdate = (String) mapResult.get("isUpdate__c");
		String jobTitleName = (String) mapResult.get("Job_Title_Name__c");
		//String lastEducation = (String) mapResult.get("Last_Education__c");
		String strDateLetter = (String) mapResult.get("Letter_Date__c");
		Date dateLetter = null;
		if(strDateLetter != null)
			dateLetter = Utils.convertStringToDate(strDateLetter);
		String letterNo = (String) mapResult.get("Letter_No__c");
		
		String maritalStatus = (String) mapResult.get("Marital_Status__c");
		String strDateMarried = (String) mapResult.get("Married_Date__c");
		Date dateMarried = null;
		if(strDateMarried != null)
			dateMarried = Utils.convertStringToDate(strDateMarried);
		String marriedPlace = (String) mapResult.get("Married_Place__c");
	
		String mobilePhone1 = (String) mapResult.get("Mobile_Phone1__c");
		String mobilePhone2 = (String) mapResult.get("Mobile_Phone2__c");
		String mobilePhone3 = (String) mapResult.get("Mobile_Phone3__c");
		String strNextBirthday = (String) mapResult.get("Next_Birthday__c");
		Date nextBirthday = null;
		if(strNextBirthday != null)
			nextBirthday = Utils.convertStringToDate(strNextBirthday);
		String nickname = (String) mapResult.get("Nickname__c");
		String strOfficialEndDate = (String) mapResult.get("Official_End_Date__c");
		Date officialEndDate = null;
		if(strOfficialEndDate != null)
			officialEndDate = Utils.convertStringToDate(strOfficialEndDate);
		String pantSize = (String) mapResult.get("Pant_Size__c");
		String strDatePassport = (String) mapResult.get("Passport_Date__c");
		Date datePassport = null;
		if(strDatePassport != null)
			datePassport = Utils.convertStringToDate(strDatePassport);
		String strDateExpiryPassport = (String) mapResult.get("Passport_Expiry_Date__c");
		Date dateExpiryPassport = null;
		if(strDateExpiryPassport != null)
			dateExpiryPassport = Utils.convertStringToDate(strDateExpiryPassport);
		String passportId = (String) mapResult.get("Passport_ID__c");
		String passportNo = (String) mapResult.get("Passport_No__c");
		String strDatePension = (String) mapResult.get("Pension_Date__c");
		Date datePension = null;
		if(strDatePension != null)
			datePension = Utils.convertStringToDate(strDatePension);
		//String performValue = (String) mapResult.get("Perform_Value__c");
		String personalEmail = (String) mapResult.get("Email__c");
		String phone = (String) mapResult.get("Phone__c");
		String phoneExt = (String) mapResult.get("Phone_Ext__c");
		//String photo = (String) mapResult.get("Photo__c");
		//String photoId = (String) mapResult.get("Photo_Id__c");
		String strDatePrePension = (String) mapResult.get("Pre_Pension_Date__c");
		Date datePrePension = null;
		if(strDatePrePension != null)
			datePrePension = Utils.convertStringToDate(strDatePrePension);

		String resignation = (String) mapResult.get("Resignation__c");
		String salutation = (String) mapResult.get("Salutation__c");
		String shoeSize = (String) mapResult.get("Shoe_Size__c");
		String skill = (String) mapResult.get("Skill__c");
		String skillLevel = (String) mapResult.get("Skill_Level__c");
		String strongPoint = (String) mapResult.get("Strong_Point__c");
		String strTerminateDate = (String) mapResult.get("Terminate_Date__c");
		Date terminateDate = null;
		if(strTerminateDate != null)
			terminateDate = Utils.convertStringToDate(strTerminateDate);
		String terminationNotes = (String) mapResult.get("Termination_Notes__c");
		String toolsAndApplication = (String) mapResult.get("Tools__c");
		String weakPoint = (String) mapResult.get("Weak_Point__c");
		// String wearGlasses = (String) mapResult.get("Wear_Glasses__c");
		String weight = (String) mapResult.get("Weight__c");
		String workInterest = (String) mapResult.get("Work_Interest__c");

//		 
		String nircNo = (String) mapResult.get("NRIC_No__c");
//	   ,
		Date nircDate = null;
		if(mapResult.get("NRIC_Date__c") != null){
			nircDate = Utils.convertStringToDate((String) mapResult
					.get("Date_of_Birth__c"));
		}
		
//		, 
		Date nircExpirationDate = null;
		if(mapResult.get("NRIC_Expiration_Date__c") != null){
			nircExpirationDate = Utils.convertStringToDate((String) mapResult
					.get("NRIC_Expiration_Date__c"));
		}
		
//		,
		String npwpNo = (String) mapResult.get("Tax_File_No__c");
//		NPWP_Date__c,
		Date npwpDate = null;
		if(mapResult.get("NPWP_Date__c") != null){
			npwpDate = Utils.convertStringToDate((String) mapResult
					.get("NPWP_Date__c"));
		}
//		,
		String nationality = (String) mapResult.get("Nationality__c");
//		Emergency_Contact_Address__c,
//		Emergency_Contact_Name__c,
//		Emergency_Contact_Phone__c,
//		Emergency_Contact_Other_Phone__c,
//		Emergency_Contact_Relationship__c
		
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setEmail(personalEmail);
		employee.setExtId(extId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setOfficeMail(officeMail);
		employee.setBirthDate(dateBirth);
		employee.setBirthPlace(birthPlace);
		employee.setMobilePhone(mobilePhone);
		employee.setBloodType(bloodType);
		employee.setEmployeeNo(employeeNo);
		employee.setReligion(religion);
		employee.setMiddleName(middleName);
		System.out.println("Age " + age);
		employee.setAge(age);
		employee.setAnniversaryDate(dateAnniversary);
		employee.setApplicantNo(applicantNo);
		employee.setAttendanceCode(attendanceCode);
		employee.setAuthorized(authorized);
		employee.setBpjsKesehatanNo(bpjsKesehatan);
		employee.setBpjsKesehatanDate(dateBpjsKesehatan);
		employee.setBpjsTKNo(bpjsKerja);
		employee.setBpjsTKDate(dateBpjsKerja);
		employee.setClothSize(clothSize);
		employee.setDialect(dialect);
		employee.setDisablity(disability);
		employee.setLicenseDrivingDate1(licenseDrivingDate1);
		employee.setLicenseExpiredDriving1(licenseExpiredDriving1);
		employee.setLicenseDrivingNo1(licenseDrivingNo1);
		employee.setLicenseDrivingDate2(licenseDrivingDate2);
		employee.setLicenseExpiredDriving2(licenseExpiredDriving2);
		employee.setLicenseDrivingNo2(licenseDrivingNo2);
		employee.setEducationTitle1(educationTitle1);
		employee.setEducationTitle2(educationTitle2);
		employee.setEmergencyContactAddress(emergencyContactAddress);
		employee.setEmergencyContactCity(emergencyContactCity);
		employee.setEmergencyContactCountry(emergencyContactCountry);
		employee.setEmergencyContactDistrict(emergencyContactDistrict);
		employee.setEmergencyContactName(emergencyContactName);
		employee.setEmergencyContactPhone(emergencyContactPhone);
		employee.setEmergencyContactOtherPhone(emergencyContactOtherPhone);
		employee.setEmergencyContactRelationship(emergencyContactRelationship);
		employee.setEmergencyContactRt(emergencyContactRt);
		employee.setEmergencyContactRw(emergencyContactRw);
		employee.setEmergencyContactStateProvince(emergencyContactStateProvince);
		employee.setEmergencyContactSubdistrict(emergencyContactSubdistrict);
		employee.setEmergencyContactZipCode(emergencyContactZipCode);
		employee.setEmployeeStatus(employeeStatus);
		employee.setFamilyCardNo(familyCardNo);
		employee.setKtpName(fktpName);
		employee.setPensionReminderMonths(pensionReminderMonths);
		employee.setFullNameTitle(fullNameTitle);
		employee.setGender(gender);
		employee.setHeadSize(headSize);
		employee.setHeight(height);
		employee.setHireDate(dateHire);
		employee.setHoby(hobby);
		employee.setInitialName(initialName);
		employee.setInterest(interest);
		employee.setJobTitleName(jobTitleName);
		employee.setNircDate(nircDate);
		employee.setNircExpirationDate(nircExpirationDate);
		employee.setLetterDate(dateLetter);
		employee.setLetterNo(letterNo);
		employee.setMaritalStatus(maritalStatus);
		employee.setMarriedDate(dateMarried);
		employee.setMarriedPlace(marriedPlace);
		employee.setMobilePhone1(mobilePhone1);
		employee.setMobilePhone2(mobilePhone2);
		employee.setMobilePhone3(mobilePhone3);
		employee.setNextBirthDate(nextBirthday);
		employee.setNickName(nickname);
		employee.setNircNo(nircNo);
		employee.setNpwpNo(npwpNo);
		employee.setNpwpDate(npwpDate);
		employee.setOfficialEndDate(officialEndDate);
		employee.setPantSize(pantSize);
		employee.setPassportDate(datePassport);
		employee.setPassportExpiryDate(dateExpiryPassport);
		employee.setPasspordId(passportId);
		employee.setPassportNo(passportNo);
		employee.setPensionData(datePension);
		employee.setPhone(phone);
		employee.setPhoneExt(phoneExt);
		employee.setPrePensionDate(datePrePension);
		employee.setResignation(resignation);
		employee.setSalution(salutation);
		employee.setShoeSize(shoeSize);
		employee.setSkill(skill);
		employee.setSkillLevel(skillLevel);
		employee.setStrongPoint(strongPoint);
		employee.setTerminationDate(terminateDate);
		employee.setTerminatioNotes(terminationNotes);
		employee.setTools(toolsAndApplication);
		employee.setWeakPoint(weakPoint);
		// employee.setWearGlasses(wearGlasses);
		employee.setWeight(weight);
		employee.setWorkInterest(workInterest);
		employee.setNationality(nationality);
		return employee;
	}

	@Override
	public void saveListData(List<Employee> listData) {
		for (Employee e : listData) {
			System.out.print("Employee : " + e.getExtId());
			// check is id is exist
			Employee empDb = employeeService.findByExtId(e.getExtId());

			// if exist doing update
			if (empDb == null) {
				empDb = new Employee();
				empDb.setCreatedDate(new Date());
			}
			empDb.setEmail(e.getEmail());
			empDb.setModifiedDate(new Date());
			empDb.setExtId(e.getExtId());
			empDb.setFirstName(e.getFirstName());
			empDb.setName(e.getName());
			empDb.setLastName(e.getLastName());
			empDb.setOfficeMail(e.getOfficeMail());
			empDb.setBirthDate(e.getBirthDate());
			empDb.setBirthPlace(e.getBirthPlace());
			empDb.setMobilePhone(e.getMobilePhone());
			empDb.setBloodType(e.getBloodType());
			empDb.setEmployeeNo(e.getEmployeeNo());
			empDb.setReligion(e.getReligion());
			empDb.setMiddleName(e.getMiddleName());
			empDb.setCompany(this.companyid);
			empDb.setModifiedBy("Talents Gateway");
			empDb.setCreatedBy("Talents Gateway");
			empDb.setAge(e.getAge());
			empDb.setAnniversaryDate(e.getAnniversaryDate());
			empDb.setApplicantNo(e.getApplicantNo());
			empDb.setAttendanceCode(e.getAttendanceCode());
			empDb.setAuthorized(e.getAuthorized());
			empDb.setBpjsKesehatanNo(e.getBpjsKesehatanNo());
			empDb.setBpjsKesehatanDate(e.getBpjsKesehatanDate());
			empDb.setBpjsTKNo(e.getBpjsTKNo());
			empDb.setBpjsTKDate(e.getBpjsTKDate());
			empDb.setClothSize(e.getClothSize());
			empDb.setDialect(e.getDialect());
			empDb.setDisablity(e.getDisablity());
			empDb.setLicenseDrivingDate1(e.getLicenseDrivingDate1());
			empDb.setLicenseExpiredDriving1(e.getLicenseExpiredDriving1());
			empDb.setLicenseDrivingNo1(e.getLicenseDrivingNo1());
			empDb.setLicenseDrivingDate2(e.getLicenseDrivingDate2());
			empDb.setLicenseExpiredDriving2(e.getLicenseExpiredDriving2());
			empDb.setLicenseDrivingNo2(e.getLicenseDrivingNo2());
			empDb.setEducationTitle1(e.getEducationTitle1());
			empDb.setEducationTitle2(e.getEducationTitle2());
			empDb.setEmergencyContactAddress(e.getEmergencyContactAddress());
			empDb.setEmergencyContactCity(e.getEmergencyContactCity());
			empDb.setEmergencyContactCountry(e.getEmergencyContactCountry());
			empDb.setEmergencyContactDistrict(e.getEmergencyContactDistrict());
			empDb.setEmergencyContactName(e.getEmergencyContactName());
			empDb.setEmergencyContactPhone(e.getEmergencyContactPhone());
			empDb.setEmergencyContactOtherPhone(e.getEmergencyContactOtherPhone());
			empDb.setEmergencyContactRelationship(e.getEmergencyContactRelationship());
			empDb.setEmergencyContactRt(e.getEmergencyContactRt());
			empDb.setEmergencyContactRw(e.getEmergencyContactRw());
			empDb.setEmergencyContactStateProvince(e.getEmergencyContactStateProvince());
			empDb.setEmergencyContactSubdistrict(e.getEmergencyContactSubdistrict());
			empDb.setEmergencyContactZipCode(e.getEmergencyContactZipCode());
			empDb.setEmployeeStatus(e.getEmployeeStatus());
			empDb.setFamilyCardNo(e.getFamilyCardNo());
			empDb.setKtpName(e.getKtpName());
			empDb.setPensionReminderDay(e.getPensionReminderDay());
			empDb.setPensionReminderMonths(e.getPensionReminderMonths());
			empDb.setFullNameTitle(e.getFullNameTitle());
			empDb.setGender(e.getGender());
			empDb.setHeadSize(e.getHeadSize());
			empDb.setHeight(e.getHeight());
			empDb.setHireDate(e.getHireDate());
			empDb.setHoby(e.getHoby());
			empDb.setInitialName(e.getInitialName());
			empDb.setInterest(e.getInterest());
			empDb.setJobTitleName(e.getJobTitleName());
			empDb.setNircDate(e.getNircDate());
			empDb.setNircExpirationDate(e.getNircExpirationDate());
			empDb.setLetterDate(e.getLetterDate());
			empDb.setLetterNo(e.getLetterNo());
			empDb.setMaritalStatus(e.getMaritalStatus());
			empDb.setMarriedDate(e.getMarriedDate());
			empDb.setMarriedPlace(e.getMarriedPlace());
			empDb.setMobilePhone1(e.getMobilePhone1());
			empDb.setMobilePhone2(e.getMobilePhone2());
			empDb.setMobilePhone3(e.getMobilePhone3());
			empDb.setNextBirthDate(e.getNextBirthDate());
			empDb.setNickName(e.getNickName());
			empDb.setNircNo(e.getNircNo());
			empDb.setNpwpNo(e.getNpwpNo());
			empDb.setNpwpDate(e.getNpwpDate());
			empDb.setOfficialEndDate(e.getOfficialEndDate());
			empDb.setPantSize(e.getPantSize());
			empDb.setPassportDate(e.getPassportDate());
			empDb.setPassportDate(e.getPassportDate());
			empDb.setPasspordId(e.getPasspordId());
			empDb.setPassportNo(e.getPassportNo());
			empDb.setPensionData(e.getPensionData());
			empDb.setPhone(e.getPhone());
			empDb.setPhoneExt(e.getPhoneExt());
			empDb.setPrePensionDate(e.getPrePensionDate());
			empDb.setResignation(e.getResignation());
			empDb.setSalution(e.getSalution());
			empDb.setShoeSize(e.getShoeSize());
			empDb.setSkill(e.getSkill());
			empDb.setSkillLevel(e.getSkillLevel());
			empDb.setStrongPoint(e.getStrongPoint());
			empDb.setTerminationDate(e.getTerminationDate());
			empDb.setTerminatioNotes(e.getTerminatioNotes());
			empDb.setTools(e.getTools());
			empDb.setWeakPoint(e.getWeakPoint());
			empDb.setWearGlasses(e.getWearGlasses());
			empDb.setWeight(e.getWeight());
			empDb.setWorkInterest(e.getWorkInterest());
			employeeService.save(empDb);
			System.out.println("Success Save Employee");
		}
	}
	
	@Override
	public void sendNewData() {
		// get data with ext id is null
		System.out.println("Send New Data");
		List<Employee> listEmployee = employeeService.findNeedSync();
		listEmployee(listEmployee);
	}
	
	
	public void listEmployee(List<Employee> listEmployee){
		if (listEmployee != null) {
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for (Employee employee : listEmployee) {
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("id", family.getId());
				if(employee.getExtId() != null)
					map.put("Id", employee.getExtId());
				
				map.put("ExtId__c", employee.getUuid());
				map.put("Birthplace__c", employee.getBirthPlace());
				map.put("Blood_Type__c", employee.getBloodType());
				map.put("Mobile_Phone__c", employee.getMobilePhone());
				map.put("Office_Email__c", employee.getOfficeMail());
				map.put("First_Name__c", employee.getFirstName());
				map.put("Last_Name__c", employee.getLastName());
				map.put("Marital_Status__c", employee.getMaritalStatus());
				map.put("Date_of_Birth__c", employee.getBirthDate());
				map.put("Middle_Name__c", employee.getMiddleName());
				map.put("Married_Date__c", employee.getMarriedDate());
				map.put("Married_Place__c", employee.getMarriedPlace());
				map.put("Anniversary_Date__c", employee.getAnniversaryDate());
				
				map.put("Religion__c", employee.getReligion());
				map.put("Email__c", employee.getEmail());
				
				
				
				listMap.add(map);
				i++;
			}
			if(listMap.size() > 0)
				send(listMap);
			System.out.println(i + " Task Already Sending ");
		}
	}
	

	
	@Override
	public void updateExtId( List<Map<String, Object>> list) {
		//System.out.println("ID " + id + " , ExtId " + extId);
		List<Map<String, Object>> listMapUpdateId = new ArrayList();
		for (Map<String, Object> map : list) {
			Map<String, Object> mapUpdateId = new HashMap<String, Object>();
			UUID uuid = (UUID) map.get("ExtId__c");
			mapUpdateId.put("ExtId__c", uuid);
			listMapUpdateId.add(mapUpdateId);
			
		}
		
		// prepare sending update Ext ID
		Map<String, Object> mapPost = new HashMap<String, Object>();
		mapPost.put("items", listMapUpdateId);
		MultiValueMap<String, String> headersPost = new LinkedMultiValueMap<String, String>();
		headersPost.add("Authorization", "Bearer " + accessToken);
		headersPost.add("Content-Type", "application/json");
		String urlQuery = this.instanceUrl + "/services/apexrest/GetIdbyExtId?SyncObject=HRPERINFO__c";
		try {
			HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(
					mapPost, headersPost);
			String response = restTemplate.postForObject(urlQuery, request,
					String.class);

			System.out.println("Reponse Post " + response);
			
			ForceResponseGetId forceResponseGetId = (ForceResponseGetId) objectMapper
					.readValue(response, ForceResponseGetId.class);
			List<Map<String,Object>> listResponseGetId = forceResponseGetId.getResults();
			
			for (Map<String, Object> map : listResponseGetId) {
				String extId = (String) map.get("Id");
				String uuid = (String) map.get("ExtId__c");
				System.out.println("Ext Id "+ extId);
				System.out.println("uuid " + uuid);
				employeeService.updateExtIdByUUID(extId, uuid);
			}
			
		} catch (HttpClientErrorException ex) {
			System.out.println("Error HTTP Client " + ex.getMessage());
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());

		}
		
	}

}
