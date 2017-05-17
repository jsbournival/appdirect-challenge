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
public class CancelSubscriptionController extends AbstractController {

	private static Logger LOGGER = LoggerFactory.getLogger(CancelSubscriptionController.class);
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@RequestMapping("/cancel")
	@Override
	public @ResponseBody AppDirectResponse handleNotification(
			@RequestParam(required = true, name = "url") String eventUrl) {
		LOGGER.info("cancel subscription controller called");
		
		try {
			subscriptionService.deleteUser(getNotification(eventUrl));
			return AppDirectResponse.builder(true).createResponse();
		} catch (Exception e) {
			return AppDirectResponse.builder(false).errorCode(ErrorCode.UNKNOWN_ERROR)
					.message(e.getMessage()).createResponse();
		}
	}
}