package com.sprinboot.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.model.Customer;
import com.sprinboot.backend.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepository categoryRepository;

	@PostMapping("/customer")
	public void postCategory(@RequestBody Customer category)
	{
		categoryRepository.save(category);
	}

	@GetMapping("/customer")
	public List<Customer> getAllCategories()
	{
		return categoryRepository.findAll();
	}

	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable("id") Long id)
	{
		Optional<Customer> optional =  categoryRepository.findById(id);

		if(optional.isPresent())
			return optional.get();
		throw new RuntimeException("Invalid customer ID " + id);
	}
}
