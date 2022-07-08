package com.objects;

public class Admin extends Employee {

	public Admin(String name, String phone, String birthdate, double salary, User user) {
		super(name, phone, birthdate, salary, user);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", phone=" + phone + ", birthdate=" + birthdate + ", salary="
				+ salary + ", user=" + user + "]";
	}

}
