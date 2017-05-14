package com.appdirect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdirect.entities.User;
import com.appdirect.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/all")
	@ResponseBody
	public String getAllUsers() {
		
		userRepository.deleteAll();

		// save a couple of customers
		userRepository.save(new User("Alice", "Smith"));
		userRepository.save(new User("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User user : userRepository.findAll()) {
			System.out.println(user);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(userRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (User user : userRepository.findByLastName("Smith")) {
			System.out.println(user);
		}		

		return "check mongo";
	}
}
