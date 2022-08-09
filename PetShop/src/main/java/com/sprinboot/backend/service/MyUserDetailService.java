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

import com.sprinboot.backend.model.Employee;
import com.sprinboot.backend.repository.EmployeeRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

//	@Autowired
//	private EmployeeController employeeController;
	
	@Autowired 
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> optional = employeeRepository.findByUsername(username);
		
		if (!optional.isPresent())
			throw new UsernameNotFoundException("Username not found");
		
		Employee emp = optional.get();
		
		List<GrantedAuthority> list = new ArrayList<>();
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority((emp.isAdmin()) ? "ADMIN" : "EMPLOYEE");
		list.add(sga);
		
		//User class from Springframework
		User user = new User(emp.getUsername(), emp.getPassword(), list);
		
		return user;
	}
	

}
