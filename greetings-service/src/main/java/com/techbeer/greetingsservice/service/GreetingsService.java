package com.techbeer.greetingsservice.service;

import java.util.Calendar;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingsService {
	private static final Logger LOG = LoggerFactory.getLogger( GreetingsService.class );
	

    
	public String getGreeting() {		
		Integer hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);		
		hour = new Random().nextInt(24); // Adicionando a geracao da hora de forma randomica para simular varios retornos.
		
		LOG.info("Recuperando greeting - hora: {}", hour);
		
		if (hour >= 6 && hour < 12) {
			return "Bom dia";
		}
		else if (hour < 19) {
			return "Boa tarde";
		}
		else {
			return "Boa noite";
		}
	}
}
