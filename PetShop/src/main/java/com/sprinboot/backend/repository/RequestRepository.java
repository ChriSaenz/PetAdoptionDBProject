package com.sprinboot.backend.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.backend.enums.Status;
import com.sprinboot.backend.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

	@Query("select r from Request r where r.pet.id=?1")
	List<Request> findByPetId(Long id);

	@Query("select r from Request r where r.employee.id=?1")
	List<Request> findByEmployeeId(Long id);

	@Query("select r from Request r where r.customer.id=?1")
	List<Request> findByCustomerId(Long id);

	@Query("select r from Request r where r.status=?1")
	List<Request> findByStatusType(Status status);

	@Query("select r from Request r where r.date<?1")
	List<Request> findBeforeDate(LocalDate date);

	@Query("select r from Request r where r.date>?1")
	List<Request> findAfterDate(LocalDate date);

	@Query("select r from Request r where r.date BETWEEN ?1 AND ?2")
	List<Request> findBetweenDate(LocalDate date, LocalDate date1);

}
