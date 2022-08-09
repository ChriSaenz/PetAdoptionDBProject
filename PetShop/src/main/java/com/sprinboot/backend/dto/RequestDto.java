/**
* Custom DTO for Requests
*
* @author  Felix Taylor
* @since   2022-07-26
*/
package com.sprinboot.backend.dto;

import java.time.LocalDate;

import com.sprinboot.backend.enums.Status;

public class RequestDto {
	
	//**********INSTANCE VARIABLES**********//
	
	private Long id;
	private LocalDate LocalDate;
	private Status status;
	private Long c_id;
	private String c_name;
	private String c_phone;
	private LocalDate c_date_joined;
	private LocalDate c_birthday;
	private Long p_id;
	private String p_name, p_species;
	private int p_age;
	private LocalDate p_date_acquired;
	private String p_sex, p_color, p_breed;
	private boolean p_vaccinated, p_neutered;
	private double p_cost;
	private Long e_id;
	private String e_name;
	
	//**********CONSTRUCTORS**********//
	
	public RequestDto() {
		super();
	}
	
	//**********GETTERS/SETTERS**********//
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return LocalDate;
	}
	public void setDate(LocalDate LocalDate) {
		this.LocalDate = LocalDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_phone() {
		return c_phone;
	}
	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}
	public LocalDate getC_date_joined() {
		return c_date_joined;
	}
	public void setC_date_joined(LocalDate c_date_joined) {
		this.c_date_joined = c_date_joined;
	}
	public LocalDate getC_birthday() {
		return c_birthday;
	}
	public void setC_birthday(LocalDate c_birthday) {
		this.c_birthday = c_birthday;
	}
	public Long getP_id() {
		return p_id;
	}
	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_species() {
		return p_species;
	}
	public void setP_species(String p_species) {
		this.p_species = p_species;
	}
	public int getP_age() {
		return p_age;
	}
	public void setP_age(int p_age) {
		this.p_age = p_age;
	}
	public LocalDate getP_date_acquired() {
		return p_date_acquired;
	}
	public void setP_date_acquired(LocalDate p_date_acquired) {
		this.p_date_acquired = p_date_acquired;
	}
	public String getP_sex() {
		return p_sex;
	}
	public void setP_sex(String p_sex) {
		this.p_sex = p_sex;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public String getP_breed() {
		return p_breed;
	}
	public void setP_breed(String p_breed) {
		this.p_breed = p_breed;
	}
	public boolean isP_vaccinated() {
		return p_vaccinated;
	}
	public void setP_vaccinated(boolean p_vaccinated) {
		this.p_vaccinated = p_vaccinated;
	}
	public boolean isP_neutered() {
		return p_neutered;
	}
	public void setP_neutered(boolean p_neutered) {
		this.p_neutered = p_neutered;
	}
	public double getP_cost() {
		return p_cost;
	}
	public void setP_cost(double p_cost) {
		this.p_cost = p_cost;
	}
	public Long getE_id() {
		return e_id;
	}
	public void setE_id(Long e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	
}
