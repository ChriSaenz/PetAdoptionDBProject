package com.objects;

public class Pet {
	int id;
	int request_id;
	String name;
	String species;
	int age;
	String date_acquired;
	String sex;
	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", species=" + species + ", age=" + age + ", date_acquired="
				+ date_acquired + ", sex=" + sex + ", color=" + color + ", breed=" + breed + ", vaccinated="
				+ vaccinated + ", neutered=" + neutered + "]";
	}
	public Pet(String name, String species, int age, String date_acquired, String sex, String color, String breed,
			boolean vaccinated, boolean neutered, String date_adopted) {
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
	}
	String color;
	String breed;
	boolean vaccinated;
	boolean neutered;
	double cost;
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
	public String getDate_acquired() {
		return date_acquired;
	}
	public void setDate_acquired(String date_acquired) {
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
}
