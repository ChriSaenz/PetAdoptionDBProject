package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping("/employee")
	public void postCategory(@RequestBody Employee category)
	{
		employeeRepository.save(category);
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)
	{
		Optional<Employee> optional =  employeeRepository.findById(id);

		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable("id")Long id)
	{
		employeeRepository.deleteById(id);
	}
}
