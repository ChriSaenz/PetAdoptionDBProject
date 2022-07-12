package com.objects;


public class Request {
	int id;
	int customer_id;
	int pet_id;
	String date;
	String status;
	int employee_id;	//	0 if request hasn't been handled yet?
	
	public Request() {
		date = "";
		status = "Invalid";
	}
	public Request(int i, int ci, int pi, String d, String s, int ei) {
		id = i;
		customer_id = ci;
		pet_id = pi;
		date = d;
		status = s;
		employee_id = ei;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	@Override
	public String toString() {
		return "Request [id=" + id + ", customer_id=" + customer_id + ", pet_id=" + pet_id + ", date=" + date
				+ ", status=" + status + ", employee_id=" + employee_id + "]";
	}
}