package com.objects;

public class Employee {
	private int id;
	private String name, phone;
	private double salary;
	private String username, password;
	private boolean admin;
	private String title;
	
	public Employee() {}
	
	// Fetching
	public Employee(int id, String name, String phone, double salary, String username, String password, boolean admin,
			String title) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.title = title;
	}
	
	// Insert
	public Employee(String name, String phone, double salary, String username, String password, boolean admin, String title) {
		super();
		this.name = name;
		this.phone = phone;
		this.salary = salary;
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.title = title;
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", salary=" + salary + ", username="
				+ username + ", password=" + password + ", admin=" + admin + ", title=" + title + "]";
	}
}