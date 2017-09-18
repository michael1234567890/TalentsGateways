package com.phincon.talents.gateways.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Home {
	
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public String home() {
        return "welcome to talents Gateway..";
    }
    

}
