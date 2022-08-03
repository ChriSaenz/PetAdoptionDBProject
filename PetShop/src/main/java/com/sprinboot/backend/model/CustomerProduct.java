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
	//TODO Needs to be an Enum, think of good names
	private String CouponCode;
	public CustomerProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerProduct(Long id, Customer customer, Product product, LocalDate dateOfPurchase, Boolean couponUsed,
			String couponCode) {
		super();
		this.id = id;
		this.customer = customer;
		this.product = product;
		this.dateOfPurchase = dateOfPurchase;
		this.couponUsed = couponUsed;
		CouponCode = couponCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Boolean getCouponUsed() {
		return couponUsed;
	}
	public void setCouponUsed(Boolean couponUsed) {
		this.couponUsed = couponUsed;
	}
	public String getCouponCode() {
		return CouponCode;
	}
	public void setCouponCode(String couponCode) {
		CouponCode = couponCode;
	}
	
}
