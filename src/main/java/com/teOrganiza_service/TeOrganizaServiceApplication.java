package com.teOrganiza_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.teOrganiza_service.identity.domain.model", "com.teOrganiza_service.finance.domain.model"})
public class TeOrganizaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeOrganizaServiceApplication.class, args);
	}

}