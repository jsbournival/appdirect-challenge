package com.appdirect.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdirect.AppDirectResponse;
import com.appdirect.ErrorCode;
import com.appdirect.services.SubscriptionService;

@Controller
@RequestMapping("/subscription")
public class CreateSubscriptionController extends AbstractController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionController.class);
	
	@Autowired
	private SubscriptionService subscriptionService;
		
	@RequestMapping(value = "/create", produces = "application/json")
	public @ResponseBody AppDirectResponse handleNotification(
			@RequestParam(required = true, name = "url") String eventUrl) {
		LOGGER.info("create subscription called");
		
		String id = null;
		try {
			id = subscriptionService.createUser(getNotification(eventUrl));
			return AppDirectResponse.builder(true).accountIdentifier(id).createResponse();
		} catch (Exception e) {
			return AppDirectResponse.builder(false).errorCode(ErrorCode.UNKNOWN_ERROR)
					.message(e.getMessage()).createResponse();
		}
	}
}
