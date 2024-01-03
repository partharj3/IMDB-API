package com.imdb.imdbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ImdbApiApplication.class, args);
		System.out.println("Lets get started...");
	}
}
