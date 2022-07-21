package com.sprinboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name, species;
	private int age;
	private LocalDate date_acquired; // Diego's Note: Shouldn't this be Date type?
	private String sex, color, breed;
	private boolean vaccinated, neutered;
	private double cost;

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

	public LocalDate getDate_acquired() {
		return date_acquired;
	}

	public void setDate_acquired(LocalDate date_acquired) {
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

	public Pet(String name, String species, int age, LocalDate date_acquired, String sex, String color, String breed,
			boolean vaccinated, boolean neutered, double cost) {
		super();
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
		return "Pet [id=" + id + ", name=" + name + ", species=" + species + ", age=" + age + ", date_acquired="
				+ date_acquired + ", sex=" + sex + ", color=" + color + ", breed=" + breed + ", vaccinated="
				+ vaccinated + ", neutered=" + neutered + ", cost=" + cost + "]";
	}
}
