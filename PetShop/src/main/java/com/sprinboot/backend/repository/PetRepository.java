package com.sprinboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

	@Query("select n from Pet n where n.name=?1")
	List<Pet> findByName(String name);
}
