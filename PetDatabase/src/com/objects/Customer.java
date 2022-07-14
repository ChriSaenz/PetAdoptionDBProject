package com.objects;

import java.sql.Date;

public class Customer {
	private int id;
	private String name, phone;
	private Date date_joined, birthdate;

	public Customer() {
	}

	// Fetching
	public Customer(int id, String name, String phone, Date date_joined, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.date_joined = date_joined;
		this.birthdate = birthdate;
	}

	// Inserting
	public Customer(String name, String phone, Date date_joined, Date birthdate) {
		super();
		this.name = name;
		this.phone = phone;
		this.date_joined = date_joined;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate_joined() {
		return date_joined;
	}

	public void setDate_joined(Date date_joined) {
		this.date_joined = date_joined;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", date_joined=" + date_joined
				+ ", birthdate=" + birthdate + "]";
	}

}
