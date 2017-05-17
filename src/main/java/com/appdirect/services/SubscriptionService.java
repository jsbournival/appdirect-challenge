package com.appdirect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appdirect.entities.User;
import com.appdirect.repositories.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SubscriptionService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a new user in the data layer
	 * 
	 * @param jsonObject
	 * @return The account identifier for the new user
	 */
	public String createUser(String jsonObject) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		// convert JSON string to User
		JsonNode root = mapper.readTree(jsonObject).at("/creator");
		User newUser =  mapper.treeToValue(root, User.class);
		
		return userRepository.save(newUser).getId();
	}
}
