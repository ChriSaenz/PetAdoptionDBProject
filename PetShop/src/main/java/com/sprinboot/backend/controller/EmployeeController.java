package com.sprinboot.backend.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.dto.EmployeeDto;
import com.sprinboot.backend.exceptions.MissingEntryException;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository categoryRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	private EmployeeDto convertToDto(Employee e) {
		EmployeeDto dto = new EmployeeDto();
		dto.setAdmin(e.isAdmin());
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setPhone(e.getPhone());
		dto.setSalary(e.getSalary());
		dto.setTitle(e.getTitle());
		dto.setUsername(e.getUsername());
		return dto;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	private List<EmployeeDto> convertToDtoList(List<Employee> list) {
		List<EmployeeDto> dtoList = new ArrayList<>();
		for (Employee e : list) {
			dtoList.add(convertToDto(e));
		}
		return dtoList;
	}

	@PostMapping("/employee")
	public EmployeeDto postEmployee(@RequestBody Employee category) {
		if (category.getPassword() != null) {
			String pw = category.getPassword();
			pw = passwordEncoder.encode(pw);
			category.setPassword(pw);
		}
		return convertToDto(categoryRepository.save(category));
	}

	@GetMapping("/employee")
	public List<EmployeeDto> getAllEmployees() {
		return convertToDtoList(categoryRepository.findAll());
	}

	@GetMapping("/employee/{id}")
	public EmployeeDto getEmployeeById(@PathVariable("id") Long id) {
		Optional<Employee> optional = categoryRepository.findById(id);

		if (optional.isPresent())
			return convertToDto(optional.get());
		throw new MissingEntryException("ID is invalid");
	}

	@GetMapping("/employee/username/{id}")
	public EmployeeDto getEmployeeDtoByUsername(@PathVariable("id") String id) {
		return convertToDto(getEmployeeByUsername(id));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	public Employee getEmployeeByUsername(String id) {
		Optional<Employee> optional = categoryRepository.findByUsername(id);

		if (optional.isPresent())
			return optional.get();
		throw new MissingEntryException("Username is invalid");
	}
	
	@GetMapping("/login")
	public EmployeeDto login(Principal pricipal) {
		return getEmployeeDtoByUsername(pricipal.getName());
	}
}
