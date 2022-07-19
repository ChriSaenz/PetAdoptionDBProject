package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.model.Pet;
import com.sprinboot.backend.repository.PetRepository;

@RestController
public class PetController {
	
	@Autowired
	private PetRepository petRepository;
	
	@PostMapping("/pet")
	public void postPet(@RequestBody Pet pet) {
		petRepository.save(pet);
	}
	
	//****************Get All Pets************************
	@GetMapping("/pet")
	public List<Pet> getAllCategories() {
		return petRepository.findAll();
	}
	
	@GetMapping("/pet/{id}")
	public Pet getPetById(@PathVariable("id") Long id) {
		Optional<Pet> optional =  petRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	
	@GetMapping("/pet/name/{name}")
	public List<Pet> getPetByName(@PathVariable("name") String name) {
		List<Pet> list = petRepository.findByName(name);
		return list;
	}
}
