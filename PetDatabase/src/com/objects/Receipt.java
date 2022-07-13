package com.objects;

public class Receipt {
	private int id, employee_id, customer_id, request_id;
	private String date;
	private double cost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", employee_id=" + employee_id + ", customer_id=" + customer_id + ", request_id="
				+ request_id + ", date=" + date + ", cost=" + cost + "]";
	}
	public Receipt(int employee_id, int customer_id, int request_id, String date, double cost) {
		super();
		this.employee_id = employee_id;
		this.customer_id = customer_id;
		this.request_id = request_id;
		this.date = date;
		this.cost = cost;
	}
	public Receipt(int id, int employee_id, int customer_id, int request_id, String date, double cost) {
		super();
		this.id = id;
		this.employee_id = employee_id;
		this.customer_id = customer_id;
		this.request_id = request_id;
		this.date = date;
		this.cost = cost;
	}
	

}
