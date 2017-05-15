package com.appdirect.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import com.appdirect.AppDirectResponse;

public abstract class AbstractController {

	@Autowired
	@Qualifier("oauthRestTemplate")
	OAuthRestTemplate restTemplate;	
	
	protected String getNotification(String eventUrl) throws Exception {
		
		URI uri = new URI(eventUrl);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

		return response.getBody();
	}
	
	public abstract AppDirectResponse handleNotification(String eventUrl);
}
