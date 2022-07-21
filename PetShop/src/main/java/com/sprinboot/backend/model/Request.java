package com.sprinboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sprinboot.backend.enums.Status;

@Entity
@Table(name = "request")
public class Request {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Request(Customer customer, Pet pet, LocalDate date, Employee employee) {
		super();
		this.customer = customer;
		this.pet = pet;
		this.date = date;
		this.employee = employee;
	}

	@OneToOne
	private Customer customer;
	@OneToOne
	private Pet pet;
	@Column(nullable = false)
	private LocalDate date;
	@Enumerated(value = EnumType.STRING)
	private Status status;
	@OneToOne
	private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", customer=" + customer + ", pet=" + pet + ", date=" + date + ", status=" + status
				+ ", employee=" + employee + "]";
	}

	public Request(Long id, Customer customer, Pet pet, LocalDate date, Status status, Employee employee) {
		super();
		this.id = id;
		this.customer = customer;
		this.pet = pet;
		this.date = date;
		this.status = status;
		this.employee = employee;
	}

	public Request(Customer customer, Pet pet, LocalDate date, Status status, Employee employee) {
		super();
		this.customer = customer;
		this.pet = pet;
		this.date = date;
		this.status = status;
		this.employee = employee;
	}

	public Request() {
		super();
	}

}
