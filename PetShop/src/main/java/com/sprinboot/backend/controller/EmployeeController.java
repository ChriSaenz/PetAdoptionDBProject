package com.sprinboot.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.dto.EmployeeDto;
import com.sprinboot.backend.exceptions.MissingEntryException;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	private EmployeeDto convertToDto(Employee e) {
		EmployeeDto dto = new EmployeeDto();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setPhone(e.getPhone());
		dto.setSalary(e.getSalary());
		dto.setTitle(e.getTitle());
		return dto;
	}
	
	private List<EmployeeDto> convertToDtoList(List<Employee> list) {
		List<EmployeeDto> dtoList = new ArrayList<>();
		for (Employee e : list) {
			dtoList.add(convertToDto(e));
		}
		return dtoList;
	}

	@PostMapping("/employee")
	public Employee postEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@GetMapping("/employee")
	public List<EmployeeDto> getAllEmployees() {
		return convertToDtoList(employeeRepository.findAll());
	}

	@GetMapping("/employee/{id}")
	public EmployeeDto getEmployeeById(@PathVariable("id") Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);

		if (optional.isPresent())
			return convertToDto(optional.get());
		throw new MissingEntryException("ID is invalid");
	}
	
	@DeleteMapping("/employee/{id}")
	public void deleteEmployeeById(@PathVariable("id")Long id)
	{
		employeeRepository.deleteById(id);
	}

//	@GetMapping("/employee/username/{id}")
//	public EmployeeDto getEmployeeDtoByUsername(@PathVariable("id") String id) {
//		return convertToDto(getEmployeeByUsername(id));
//	}
//	
//	public Employee getEmployeeByUsername(String id) {
//		Optional<Employee> optional = employeeRepository.findByUsername(id);
//
//		if (optional.isPresent())
//			return optional.get();
//		throw new MissingEntryException("Username is invalid");
//	}
	
//	@GetMapping("/login")
//	public UserLoginDto login(Principal principal)
//	{
//		String username = principal.getName();
//		
//	}
}
