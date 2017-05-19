package com.appdirect.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appdirect.ErrorCode;
import com.appdirect.entities.User;
import com.appdirect.exceptions.AppDirectException;
import com.appdirect.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SubscriptionService {

	private static Logger LOGGER = LoggerFactory.getLogger(SubscriptionService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper jsonMapper;

	/**
	 * Creates a new user in the data layer
	 * 
	 * @param jsonObject
	 * @return The account identifier for the new user
	 */
	public String createUser(String jsonObject) throws AppDirectException {
		LOGGER.info("create user service called");

		try {
			// convert JSON string to User
			JsonNode root = jsonMapper.readTree(jsonObject).at("/creator");
			User newUser = jsonMapper.treeToValue(root, User.class);

			return userRepository.save(newUser).getId();
		} catch (IOException e) {
			throw new AppDirectException(ErrorCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * Deletes a user from the data layer
	 * 
	 * @param jsonObject
	 * @return The account identifier for the deleted user
	 */
	public void deleteUser(String jsonObject) throws AppDirectException {
		LOGGER.info("delete user service called");

		try {
			LOGGER.debug(jsonObject);
			
			// retrieve account to delete
			String id = jsonMapper.readTree(jsonObject).at("/payload/account/accountIdentifier").asText();

			LOGGER.debug("deleting user {}", id);
			userRepository.delete(id);
		} catch (IOException e) {
			throw new AppDirectException(ErrorCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
}
