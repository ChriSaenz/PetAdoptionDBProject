package com.springboot.microservice.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PetShopMicroserviceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopMicroserviceOrderServiceApplication.class, args);
	}

}
