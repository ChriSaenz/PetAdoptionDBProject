package com.sprinboot.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sprinboot.backend.controller.EmployeeController;
import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private EmployeeController employeeController;
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> optional = employeeRepository.findByUsername(username);
		
		if (!optional.isPresent())
			throw new UsernameNotFoundException("Username not found");
		
		Employee emp = optional.get();
		
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(ui.getRole());
		
		String role = "EMPLOYEE";
		if (employee.isAdmin()) role = "ADMIN";
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role));
		return new User(username, employee.getPassword(), list);
	}
	

}
