package com.techbeer.usergreetingsservice.consumer;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingsConsumer {
	private static final Logger LOG = LoggerFactory.getLogger( GreetingsConsumer.class );

	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "getGreetingFallback")
	public String getGreeting() {
		LOG.info("-> Iniciando chamada ao serviço.");
		
		URI uri = URI.create("http://greetings-service/greetings");
		String greeting = restTemplate.getForObject(uri, String.class);
		
		LOG.info("---> Chamada NORMAL ao serviço.");
		
		return greeting;
	}
	
	public String getGreetingFallback() {
		LOG.info("---> Chamada via FALLBACK.");
		
		return "[FALLBACK] Olá";
	}
}
