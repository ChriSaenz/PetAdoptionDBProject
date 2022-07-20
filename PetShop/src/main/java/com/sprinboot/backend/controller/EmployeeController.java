package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	private EmployeeRepository categoryRepository;

	@PostMapping("/employee")
	public void postCategory(@RequestBody Employee category)
	{
		categoryRepository.save(category);
	}

	@GetMapping("/employee")
	public List<Employee> getAllCategories()
	{
		return categoryRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)
	{
		Optional<Employee> optional =  categoryRepository.findById(id);

		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("ID is invalid");
	}
}
