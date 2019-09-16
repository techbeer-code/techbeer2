package com.techbeer.greetingsservice.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techbeer.greetingsservice.service.GreetingsService;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {
		
	@Autowired
	private GreetingsService service;
	    
	@GetMapping
	public String getGreeting(HttpServletRequest request) throws UnknownHostException {
		// Adicionando no retorno o nome e porta do servidor para visualizarmos o Load Balance.
		String serverName = "[" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getLocalPort() + "] ";
		
		return serverName + service.getGreeting();
	}
}
