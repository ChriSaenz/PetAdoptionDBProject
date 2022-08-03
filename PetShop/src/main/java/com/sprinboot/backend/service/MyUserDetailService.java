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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private EmployeeRepository employeeController;
	
	@Override
	@CrossOrigin(origins = "http://localhost:4200")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employeeO = employeeController.findByUsername(username);
		if (employeeO == null)
			throw new UsernameNotFoundException("username not found");
		Employee employee = employeeO.get();
		String role = "EMPLOYEE";
		if (employee.isAdmin()) role = "ADMIN";
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role));
		return new User(username, employee.getPassword(), list);
	}
	

}
