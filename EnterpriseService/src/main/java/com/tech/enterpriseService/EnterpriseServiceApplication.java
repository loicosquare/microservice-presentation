package com.tech.enterpriseService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EnterpriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseServiceApplication.class, args);
	}

}
