package com.springboot.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PetShopMicroserviceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopMicroserviceEurekaServerApplication.class, args);
	}

}
