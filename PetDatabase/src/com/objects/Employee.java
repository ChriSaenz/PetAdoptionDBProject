package com.objects;

public class Employee {

	int id;
	String name;
	String phone;
	String birthdate;
	double salary;
	User user;
	public Employee(String name, String phone, String birthdate, double salary, User user) {
		super();
		this.name = name;
		this.phone = phone;
		this.birthdate = birthdate;
		this.salary = salary;
		this.user = user;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", birthdate=" + birthdate + ", salary="
				+ salary + ", user=" + user + "]";
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
