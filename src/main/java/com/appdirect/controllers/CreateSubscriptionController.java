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
public class CreateSubscriptionController extends AbstractController {

	private static Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionController.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Override
	@RequestMapping(value = "/create", produces = "application/json")
	public @ResponseBody AppDirectResponse handleNotification(
			@RequestParam(required = true, name = URL_PARAMETER) String eventUrl) throws AppDirectException {
		LOGGER.info("create subscription controller called");

		String id = subscriptionService.createUser(getNotification(eventUrl));
		return AppDirectResponse.builder(true).accountIdentifier(id).createResponse();
	}
}
