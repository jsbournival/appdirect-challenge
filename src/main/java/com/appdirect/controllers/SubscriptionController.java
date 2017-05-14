package com.appdirect.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.appdirect.AppDirectResponse;
import com.appdirect.ErrorCode;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

	@RequestMapping(value = "/create", produces = "application/json")
	public @ResponseBody AppDirectResponse createSubscription(
			@RequestParam(required = true, name = "url") String eventUrl) {

		String ret = "nothing";

		try {
			URI uri = new URI(eventUrl);

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

			ret = response.getBody();

		} catch (Exception e) {
			return AppDirectResponse.builder(false).errorCode(ErrorCode.UNKNOWN_ERROR)
					.message(e.getMessage()).createResponse();
		}

		return AppDirectResponse.builder(true).accountIdentifier("TODO").createResponse();
	}
}