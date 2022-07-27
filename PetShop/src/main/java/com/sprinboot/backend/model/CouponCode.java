package com.sprinboot.backend.model;

public enum CouponCode {
	DIS40JULY(40), DIS30JULY(30), DIS20JULY(20), DIS10JULY(10);
	
	private double discount;
	
	CouponCode(double discount) {
		this.discount = discount;
	}

	public double getDiscount() {
		return discount;
	}
}
