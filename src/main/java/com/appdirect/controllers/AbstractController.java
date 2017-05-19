package com.appdirect.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;

import com.appdirect.AppDirectResponse;
import com.appdirect.ErrorCode;
import com.appdirect.exceptions.AppDirectException;

@RequestMapping("/subscription")
public abstract class AbstractController {

	protected final static String URL_PARAMETER = "url";
	
	@Autowired
	@Qualifier("oauthRestTemplate")
	OAuthRestTemplate restTemplate;

	protected String getNotification(String eventUrl) throws AppDirectException {

		try {
			URI uri = new URI(eventUrl);
			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

			return response.getBody();
		} catch (URISyntaxException|RestClientException e) {
			throw new AppDirectException(ErrorCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	public abstract AppDirectResponse handleNotification(String eventUrl) throws AppDirectException;
}
