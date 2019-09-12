package com.techbeer.usergreetingsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbeer.usergreetingsservice.consumer.GreetingsConsumer;

@Service
public class UserGrettingsService {

	@Autowired
	private GreetingsConsumer consumer;
	
	public String getUserGreeting(String name) {
		String greeting = consumer.getGreeting();
		
		return greeting + " " + name; 
	}
}
