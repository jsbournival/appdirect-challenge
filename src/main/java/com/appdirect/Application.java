package com.appdirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

@SpringBootApplication
public class Application {

	@Bean
	public OAuthRestTemplate oAuthRestTemplate() {

		BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey("group-membership-164662");
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl("fvn6oeuA6rgIWe0E"));

		return new OAuthRestTemplate(resourceDetails);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}