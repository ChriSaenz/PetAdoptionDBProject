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
	
	@GetMapping("/pet/{id}")
	public Pet getPetById(@PathVariable("id") Long id) {
		Optional<Pet> optional =  petRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	
	//****************Add New Pet************************
	@PostMapping("/pet")
	public void postPet(@RequestBody Pet pet) {
		petRepository.save(pet);
	}
	
	//****************View All Pets**********************
	@GetMapping("/pet")
	public List<Pet> getAllPets() {
		return petRepository.findAll();
	}
	
	//****************Filter Pets By Name*******************
	@GetMapping("/pet/name/{name}")
	public List<Pet> filterPetByName(@PathVariable("name") String name) {
		List<Pet> list = petRepository.findByName(name);
		return list;
	}
	
	//*************Filter Pets By Species****************
	@GetMapping("/pet/species/{species}")
	public List<Pet> filterPetBySpecies(@PathVariable("species") String species) {
		List<Pet> list = petRepository.filterBySpecies(species);
		return list;
	}
	
	//*************Filter Pets By Age****************
	@GetMapping("/pet/age/{age}")
	public List<Pet> filterPetByAge(@PathVariable("age") int age) {
		List<Pet> list = petRepository.filterByAge(age);
		return list;
	}
	
	//*************Filter Pets By Sex****************
	@GetMapping("/pet/sex/{sex}")
	public List<Pet> filterPetByAge(@PathVariable("sex") String sex) {
		List<Pet> list = petRepository.filterBySex(sex);
		return list;
	}
	

	//*************Filter Pets By Color****************
	@GetMapping("/pet/color/{color}")
	public List<Pet> filterPetByColor(@PathVariable("color") String color) {
		List<Pet> list = petRepository.filterByColor(color);
		return list;
	}
	
	//*************Filter Pets By Breed****************
	@GetMapping("/pet/breed/{breed}")
	public List<Pet> filterPetByBreed(@PathVariable("breed") String breed) {
		List<Pet> list = petRepository.filterByBreed(breed);
		return list;
	}
	
	//*************Filter Pets By Vaccinated****************
	@GetMapping("/pet/vaccinated/{vaccinated}")
	public List<Pet> filterPetByVaccinated(@PathVariable("vaccinated") boolean vaccinated) {
		List<Pet> list = petRepository.filterByVaccinated(vaccinated);
		return list;
	}

	//*************Filter Pets By Neutered****************
	@GetMapping("/pet/neutered/{neutered}")
	public List<Pet> filterPetByNeutered(@PathVariable("neutered") boolean neutered) {
		List<Pet> list = petRepository.filterByNeutered(neutered);
		return list;
	}
	
	//*************Filter Pets By Everything****************
//	@GetMapping("/pet/name/{na}/species/{sp}/age/{a}/sex/{se}/color/{c}/breed/{b}/vaccinated/{v}/neutered/{n}")
//	public List<Pet> filterPetByAll(@PathVariable("na") String name,
//			@PathVariable("sp") String species,
//			@PathVariable("a") int age,
//			@PathVariable("se") String sex,
//			@PathVariable("c") String color,
//			@PathVariable("b") String breed,
//			@PathVariable("v") boolean vaccinated,
//			@PathVariable("ne") boolean neutered) {
//		return petRepository.filterByAll(name, species, age, sex, color, breed, vaccinated, neutered);
//	}
}
