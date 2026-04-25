package com.app.mazda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringThyemleafApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringThyemleafApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// This method will be executed after the application context is loaded and right before the Spring Application run method is completed.
		System.out.println("Application started with CommandLineRunner");
	}
}
