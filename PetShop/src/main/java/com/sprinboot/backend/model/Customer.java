package com.sprinboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String phone;
	private LocalDate date_joined;
	private LocalDate birthday;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDate getDate_joined() {
		return date_joined;
	}

	public void setDate_joined(LocalDate date_joined) {
		this.date_joined = date_joined;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Customer(Long id, String name, String phone, LocalDate date_joined, LocalDate birthday) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.date_joined = date_joined;
		this.birthday = birthday;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", date_joined=" + date_joined
				+ ", birthday=" + birthday + "]";
	}
	
	
}
