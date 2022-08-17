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
	
	@Query("select s from Pet s where s.sex=?1")
	List<Pet> filterBySex(String sex);
	
	@Query("select c from Pet c where c.color=?1")
	List<Pet> filterByColor(String color);
	
	@Query("select b from Pet b where b.breed=?1")
	List<Pet> filterByBreed(String breed);
	
	@Query("select v from Pet v where v.vaccinated=?1")
	List<Pet> filterByVaccinated(boolean vaccinated);
	
	@Query("select n from Pet n where n.neutered=?1")
	List<Pet> filterByNeutered(boolean neutered);
	
//		I would like to know why this didn't work someday
//	@Query("select x from Pet x where ?1=?2")
//	List<Pet> filterByFilter(String filter, String value);
}
