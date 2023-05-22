package com.prova.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repository")
public class PrivacyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivacyApplication.class, args);
	}

}
