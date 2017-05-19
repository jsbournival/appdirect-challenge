package com.appdirect.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdirect.AppDirectResponse;
import com.appdirect.exceptions.AppDirectException;
import com.appdirect.services.SubscriptionService;

@Controller
public class CancelSubscriptionController extends AbstractController {

	private static Logger LOGGER = LoggerFactory.getLogger(CancelSubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Override
	@RequestMapping("/cancel")
	public @ResponseBody AppDirectResponse handleNotification(
			@RequestParam(required = true, name = URL_PARAMETER) String eventUrl) throws AppDirectException {
		LOGGER.info("cancel subscription controller called");

		subscriptionService.deleteUser(getNotification(eventUrl));
		return AppDirectResponse.builder(true).createResponse();
	}
}