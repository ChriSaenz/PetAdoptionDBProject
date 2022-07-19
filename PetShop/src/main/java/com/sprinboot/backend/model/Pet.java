package com.sprinboot.backend.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int request_id;
	private String name, species;
	private int age;
	private Date date_acquired; // Diego's Note: Shouldn't this be Date type?
	private String sex, color, breed;
	private boolean vaccinated, neutered;
	private double cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRequest_id() {
		return request_id;
	}
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate_acquired() {
		return date_acquired;
	}
	public void setDate_acquired(Date date_acquired) {
		this.date_acquired = date_acquired;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public boolean isVaccinated() {
		return vaccinated;
	}
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}
	public boolean isNeutered() {
		return neutered;
	}
	public void setNeutered(boolean neutered) {
		this.neutered = neutered;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Pet() {
		super();
	}
	
	public Pet(int request_id, String name, String species, int age, Date date_acquired, String sex, String color,
			String breed, boolean vaccinated, boolean neutered, double cost) {
		super();
		this.request_id = request_id;
		this.name = name;
		this.species = species;
		this.age = age;
		this.date_acquired = date_acquired;
		this.sex = sex;
		this.color = color;
		this.breed = breed;
		this.vaccinated = vaccinated;
		this.neutered = neutered;
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Pet [id=" + id + ", request_id=" + request_id + ", name=" + name + ", species=" + species + ", age="
				+ age + ", date_acquired=" + date_acquired + ", sex=" + sex + ", color=" + color + ", breed=" + breed
				+ ", vaccinated=" + vaccinated + ", neutered=" + neutered + ", cost=" + cost + "]";
	}
}
