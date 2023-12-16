package com.tech.entrepriseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EntrepriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntrepriseServiceApplication.class, args);
	}

}
