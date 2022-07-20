package com.sprinboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.backend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

}
