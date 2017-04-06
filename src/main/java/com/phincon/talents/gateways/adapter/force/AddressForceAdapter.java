package com.phincon.talents.gateways.adapter.force;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.gateways.model.Address;
import com.phincon.talents.gateways.services.AddressService;

@Service
public class AddressForceAdapter extends ForceAdapter<Address> {

	@Autowired
	AddressService addressService;
	
	public AddressForceAdapter() {
		super();
		query = "select Id,Address__c, Address_Distance__c, Address_Status__c, Address_Type__c, City__c, Country__c, District__c, Full_Name__c, Phone__c, Residence__c, RT__c, RW__c, State_Province__c, Stay_Status__c, Subdistrict__c, ZIP_Code__c "
				+ "from HRPERADDRESS__c limit 10";
	}
	
	@Override
	protected Address convertMapResultToObject(Map<String, Object> mapResult){
		String extId = (String) mapResult.get("Id");
		String address = (String) mapResult.get("Address__c");
		String addressDistance = (String) mapResult.get("Address_Distance__c");
		String addressStatus = (String) mapResult.get("Address_Status__c");
		String addressType = (String) mapResult.get("Address_Type__c");
		String city = (String) mapResult.get("City__c");
		String country = (String) mapResult.get("Country__c");
		String district = (String) mapResult.get("District__c");
		//String fullName = (String) mapResult.get("Full_Name__c");
		String phone = (String) mapResult.get("Phone__c");
		String residence = (String) mapResult.get("Residence__c");
		String rt = (String) mapResult.get("RT__c");
		String rw = (String) mapResult.get("RW__c");
		String stateProvince = (String) mapResult.get("State_Province__c");
		String stayStatus = (String) mapResult.get("Stay_Status__c");
		String subDistrict = (String) mapResult.get("Subdistrict__c");
		String zipCode = (String) mapResult.get("ZIP_Code__c");
		
		Address address1 = new Address();
		address1.setExtId(extId);
		address1.setAddress(address);
		address1.setDistance(addressDistance);
		address1.setAddressStatus(addressStatus);
		address1.setType(addressType);
		address1.setCity(city);
		address1.setCountry(country);
		address1.setDistrict(district);
		address1.setPhone(phone);
		address1.setResidence(residence);
		address1.setRt(rt);
		address1.setRw(rw);
		address1.setProvince(stateProvince);
		address1.setStayStatus(stayStatus);
		address1.setSubdistrict(subDistrict);
		address1.setZipCode(zipCode);
		
		return address1;
	}
	
	@Override
	public void saveListDate(List<Address> listData){
		for(Address e : listData){
			System.out.println("Address : " + e.getExtId());
			Address addrsDb = addressService.findByExtId(e.getExtId());
			
			if(addrsDb == null){
				addrsDb = new Address();
			}
			addrsDb.setExtId(e.getExtId());
			addrsDb.setAddress(e.getAddress());
			addrsDb.setDistance(e.getDistance());
			addrsDb.setAddressStatus(e.getAddressStatus());
			addrsDb.setType(e.getType());
			addrsDb.setCity(e.getCity());
			addrsDb.setCountry(e.getCountry());
			addrsDb.setDistrict(e.getDistrict());
			addrsDb.setPhone(e.getPhone());
			addrsDb.setResidence(e.getResidence());
			addrsDb.setRt(e.getRt());
			addrsDb.setRw(e.getRw());
			addrsDb.setProvince(e.getProvince());
			addrsDb.setStayStatus(e.getStayStatus());
			addrsDb.setSubdistrict(e.getSubdistrict());
			addrsDb.setZipCode(e.getZipCode());
			
			addressService.save(addrsDb);
			System.out.println("Success Save Address");
		}
	}

}
