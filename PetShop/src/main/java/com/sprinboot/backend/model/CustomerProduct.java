package com.sprinboot.backend.model;

import java.time.LocalDate;

import javax.persistence.*;

// A class that 
public class CustomerProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Customer customer;
	@OneToOne
	private Product product;
	
	private LocalDate dateOfPurchase;
	private Boolean couponUsed;
	private String CouponCode;
}
