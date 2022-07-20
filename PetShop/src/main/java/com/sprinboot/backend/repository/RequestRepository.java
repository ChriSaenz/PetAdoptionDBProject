package com.sprinboot.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	@Query("select r from request where request.pet.id=?1")
	List<Request> findByPetId(Long id);

	@Query("select r from request where request.employee.id=?1")
	List<Request> findByEmployeeId(Long id);

	@Query("select r from request where request.customer.id=?1")
	List<Request> findByCustomerId(Long id);

	@Query("select r from request where request.status.id=?1")
	List<Request> findByStatusId(Long id);

	@Query("select r from request where request.status.type=?1")
	List<Request> findByStatusType(String type);

}
