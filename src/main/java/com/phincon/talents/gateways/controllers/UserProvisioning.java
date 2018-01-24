package com.phincon.talents.gateways.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.gateways.adapter.UsersAdapter;
import com.phincon.talents.gateways.services.UsersService;


@Controller
@RequestMapping("/userprovision")
public class UserProvisioning {
	
	
	@Autowired
	UsersAdapter usersAdapter;

	@Autowired
	UsersService usersService;

		
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	@ResponseBody
	public String createNewUser() {		
		usersAdapter.automaticUserCreation();
		return "user provisioning done";
	}
	

}