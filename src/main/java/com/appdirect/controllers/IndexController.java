package com.appdirect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.appdirect.services.SubscriptionService;

@Controller
public class IndexController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		model.addAttribute("users", subscriptionService.findAllUsers());
		
		return "index";
	}
}
