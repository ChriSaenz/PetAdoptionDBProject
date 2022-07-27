package com.springboot.microservice.orderservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	//	Test URL to see if OrderController works
	@GetMapping("/test")
	public String test() {
		return "OrderController active";
	}
	
	//	Possible method for making orders?
	public void placeOrder() {
		//	Enter customer information
		//	Select pet
		//	Create request, set request to Pending
	}
	
	//	
	@PutMapping("/request/handle")
	public void handleRequest() {
		
	}
}
