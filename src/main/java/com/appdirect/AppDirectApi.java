package com.appdirect;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

@Component
public class AppDirectApi {

	@Autowired
	@Qualifier("oAuthConsumer")
	OAuthConsumer consumer;
	
	public String call(URL url) throws IOException, OAuthException {
		
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		consumer.sign(request);
		request.connect();
		
		return "todo";
	}
}
