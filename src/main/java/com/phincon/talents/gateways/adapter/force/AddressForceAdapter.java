package com.phincon.talents.gateways.adapter.force;

import java.util.ArrayList;
import java.util.HashMap;
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
		query = "GetAllHRPERADDRESS";
	}
	
	@Override
	protected Address convertMapResultToObject(Map<String, Object> mapResult){
		String extId = "";
		if(mapResult.get("Id") != null){
			extId = (String) mapResult.get("Id");
		}else{
			extId = null;
		}
		String address = "";
		if(mapResult.get("Address__c") != null){
			address = (String) mapResult.get("Address__c");
		}else{
			address = null;
		}
		String addressDistance = "";
		if(mapResult.get("Address_Distance__c") != null){
			addressDistance = (String) mapResult.get("Address_Distance__c");
		}else{
			addressDistance = null;
		}
		String addressStatus = "";
		if(mapResult.get("Address_Status__c") != null){
			addressStatus = (String) mapResult.get("Address_Status__c");
		}else{
			addressStatus = null;
		}
		String addressType = "";
		if(mapResult.get("Address_Type__c") != null){
			addressType = (String) mapResult.get("Address_Type__c");
		}else{
			addressType = null;
		}
		String city = "";
		if(mapResult.get("City__c") != null){
			city = (String) mapResult.get("City__c");
		}else{
			city = null;
		}
		String country = "";
		if(mapResult.get("Country__c") != null){
			country = (String) mapResult.get("Country__c");
		}else{
			country = null;
		}
		String district = "";
		if(mapResult.get("District__c") != null){
			district = (String) mapResult.get("District__c");
		}else{
			district = null;
		}
		//String fullName = (String) mapResult.get("Full_Name__c");
		String phone = "";
		if(mapResult.get("Phone__c") != null){
			phone = (String) mapResult.get("Phone__c");
		}else{
			phone = null;
		}
		String residence = "";
		if(mapResult.get("Residence__c") != null){
			residence = (String) mapResult.get("Residence__c");
		}else{
			residence = null;
		}
		String rt = "";
		if(mapResult.get("RT__c") != null){
			rt = (String) mapResult.get("RT__c");
		}else{
			rt = null;
		}
		String rw = "";
		if(mapResult.get("RW__c") != null){
			rw = (String) mapResult.get("RW__c");
		}else{
			rw = null;
		}
		String stateProvince = "";
		if(mapResult.get("State_Province__c") != null){
			stateProvince = (String) mapResult.get("State_Province__c");
		}else{
			stateProvince = null;
		}
		String stayStatus = "";
		if(mapResult.get("Stay_Status__c") != null){
			stayStatus = (String) mapResult.get("Stay_Status__c");
		}else{
			stayStatus = null;
		}
		String subDistrict = "";
		if(mapResult.get("Subdistrict__c") != null){
			subDistrict = (String) mapResult.get("Subdistrict__c");
		}else{
			subDistrict = null;
		}
		String zipCode = "";
		if(mapResult.get("ZIP_Code__c") != null){
			zipCode = (String) mapResult.get("ZIP_Code__c");
		}else{
			zipCode = null;
		}
		
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
	
	public void sendNewData(){
		// get data with ext id is null
		System.out.println("Send New Data");
		Iterable<Address> listAddress = addressService.findAllExtIdNull();
		if(listAddress != null){
			int i = 0;
			List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			for(Address address : listAddress){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("Id", address.getId());
				map.put("Address__c", address.getAddress());
				map.put("Address_Distance__c", address.getDistance());
				map.put("Address_Status__c", address.getAddressStatus());
				map.put("Address_Type__c", address.getType());
				map.put("City__c", address.getCity());
				map.put("Country__c", address.getCountry());
				map.put("District__c", address.getDistrict());
				map.put("Phone__c", address.getPhone());
				map.put("Residence__c", address.getResidence());
				map.put("RT__c", address.getRt());
				map.put("RW__c", address.getRw());
				map.put("State_Province__c", address.getProvince());
				map.put("Stay_Status__c", address.getStayStatus());
				map.put("Subdistrict__c", address.getSubdistrict());
				map.put("ZIP_Code__c", address.getZipCode());
				listMap.add(map);
				System.out.print("No " + (i+1) + " : " + address.toString());
				i++;
			}
			if(listMap.size() > 0)
				send(listMap);
			System.out.println(i + " Task Already Sending");
		}
	}
	
	@Override
	public void updateExtId(String extId, Long id){
		System.out.println("ID " + id + " , ExtId " + extId);
		addressService.updateExtIdById(extId, id);
	}
}
