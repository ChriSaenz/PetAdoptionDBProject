package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.exceptions.MissingEntryException;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository categoryRepository;
	private PasswordEncoder passwordEncoder;


	@PostMapping("/employee")
	public void postEmployee(@RequestBody Employee category)
	{
		String pw = category.getPassword();
		pw = passwordEncoder.encode(pw);
		category.setPassword(pw);
		categoryRepository.save(category);
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmployees()
	{
		return categoryRepository.findAll();
	}

	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id)
	{
		Optional<Employee> optional =  categoryRepository.findById(id);

		if(optional.isPresent())
			return optional.get();
		throw new MissingEntryException("ID is invalid");
	}
	

	@GetMapping("/employee/username/{id}")
	public Employee getEmployeeByUsername(@PathVariable("id") String id)
	{
		Optional<Employee> optional =  categoryRepository.findByUsername(id);

		if(optional.isPresent())
			return optional.get();
		throw new MissingEntryException("Username is invalid");
	}
}
