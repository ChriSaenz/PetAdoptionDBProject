package com.objects;
import java.sql.Date;
import java.util.Calendar;

public class Request {
	private int id, customer_id, pet_id;
	private Date date;
	private String status;
	private int employee_id;	//	0 if request hasn't been handled yet?
	
	public Request() {
		status = "Pending";
	}
	public Request(int i, int ci, int pi, Date d, String s, int ei) {
		id = i;
		customer_id = ci;
		pet_id = pi;
		date = d;
		status = s;
		employee_id = ei;
	}
	

	public Request(int customer_id, int pet_id, int employee_id) {
		super();
		this.customer_id = customer_id;
		this.pet_id = pet_id;
		date = new Date(Calendar.getInstance().getTime().getTime());
		this.employee_id = employee_id;
		status = "Pending";
	}
	public int getId() {
		return id;
	}
	public Request(int customer_id, int pet_id, Date date, String status, int employee_id) {
		super();
		this.customer_id = customer_id;
		this.pet_id = pet_id;
		this.date = date;
		this.status = status;
		this.employee_id = employee_id;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
