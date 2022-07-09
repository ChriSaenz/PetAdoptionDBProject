package com.objects;

import java.sql.Date;

public class Customer {
	int id;
	String name;
	String phone_number;
	Date date_joined;
	Date birthday;
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public java.sql.Date getDate_joined() {
		return date_joined;
	}
	public void setDate_joined(Date date_joined) {
		this.date_joined = date_joined;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone_number=" + phone_number + ", date_joined="
				+ date_joined + ", birthday=" + birthday + "]";
	}
	public Customer(int id, String name, String phone_number, Date date_joined, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.date_joined = date_joined;
		this.birthday = birthday;
	}
	public Customer(String name, String phone_number, Date date, Date date1) {
		super();
		this.name = name;
		this.phone_number = phone_number;
		this.date_joined = date;
		this.birthday = date1;
	}
	
	
}