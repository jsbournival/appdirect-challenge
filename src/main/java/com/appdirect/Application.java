package com.appdirect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Value("${oauth.key}")
	private String oauthKey;

	@Value("${oauth.secret}")
	private String oauthSecret;

	@Bean
	public OAuthRestTemplate oauthRestTemplate() {
		return new OAuthRestTemplate(getProtectedResourceDetails());
	}

	public ProtectedResourceDetails getProtectedResourceDetails() {

		LOGGER.info("config oauth with values {} {}", oauthKey, oauthSecret);
		
		final BaseProtectedResourceDetails resource = new BaseProtectedResourceDetails();
		resource.setConsumerKey(oauthKey);
		resource.setSharedSecret(new SharedConsumerSecretImpl(oauthSecret));
		return resource;
	}

	@Bean
	public OAuthConsumer oAuthConsumer() {
		return new DefaultOAuthConsumer("group-membership-164662", "fvn6oeuA6rgIWe0E");
	}
	
	@Bean
	public ObjectMapper jsonMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}