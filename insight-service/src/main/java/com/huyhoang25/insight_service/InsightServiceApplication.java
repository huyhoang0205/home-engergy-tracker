package com.huyhoang25.insight_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class InsightServiceApplication {

	public static void main(String[] args) {
		// Load environment variables from the .env file into System Properties.
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(InsightServiceApplication.class, args);
	}

}
