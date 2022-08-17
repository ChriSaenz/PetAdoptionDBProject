package com.sprinboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{
	
	@Query("select n from Pet n where n.name=?1")
	List<Pet> findByName(String name);
	
	@Query("select s from Pet s where s.species=?1")
	List<Pet> filterBySpecies(String species);
	
	@Query("select a from Pet a where a.age=?1")
	List<Pet> filterByAge(int age);
	
	@Query("select x from Pet x where ?1 = ?2")
	List<Pet> filterByFilter(String filter, String value);
}
