package com.sprinboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sprinboot.backend.model.Employee;

@CrossOrigin("http://localhost:4200")
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

//	@Query("select e from Employee e where e.username=?1")
//	Optional<Employee> findByUsername(String id);
}
