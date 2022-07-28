/**
* RequestRepository holds and retrieves all request entries
* in the database.
*
* @author  Felix Taylor
* @since   2022-07-26
*/

package com.sprinboot.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.enums.Status;
import com.sprinboot.backend.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	// Select requests that contain a particular pet ID
	@Query("select r from Request r where r.pet.id=?1")
	List<Request> findByPetId(Long id);

	// Select requests that contain a particular employee ID
	@Query("select r from Request r where r.employee.id=?1")
	List<Request> findByEmployeeId(Long id);

	// Select requests that contain a particular customer ID
	@Query("select r from Request r where r.customer.id=?1")
	List<Request> findByCustomerId(Long id);

	// Select requests that contain a particular status
	@Query("select r from Request r where r.status=?1")
	List<Request> findByStatusType(Status status);

	// Select requests that contain a date before a particular date
	@Query("select r from Request r where r.date<?1")
	List<Request> findBeforeDate(LocalDate date);

	// Select requests that contain a date after a particular date
	@Query("select r from Request r where r.date>?1")
	List<Request> findAfterDate(LocalDate date);

	// Select requests that contain a date between two dates
	@Query("select r from Request r where r.date BETWEEN ?1 AND ?2")
	List<Request> findBetweenDate(LocalDate date, LocalDate date1);

	// Select requests of a particular species of pet
	@Query("select r from Request r where r.pet.species=?1")
	List<Request> findBySpecies(String species);

	// Select requests of a particular breed of pet
	@Query("select r from Request r where r.pet.breed=?1")
	List<Request> findByBreed(String breed);
	
	// Select requests of a particular pet color
	@Query("select r from Request r where r.pet.color=?1")
	List<Request> findByColor(String color);

}
