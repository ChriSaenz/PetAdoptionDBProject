package com.objects;

public class Customer {
	int id;
	String name;
	String phone;
	String birthdate;
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", birthdate=" + birthdate+"]";
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Customer(String name, String phone, String birthdate) {
		super();
		this.name = name;
		this.phone = phone;
		this.birthdate = birthdate;
	}

}
