package com.sprinboot.backend.service;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private EmployeeController employeeController;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeController.getEmployeeByUsername(username);
		if (employee == null)
			throw new UsernameNotFoundException("username not found");
		String role = "EMPLOYEE";
		if (employee.isAdmin()) role = "ADMIN";
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(role));
		return new User(username, employee.getPassword(), list);
	}
	

}
