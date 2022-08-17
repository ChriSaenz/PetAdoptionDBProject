package com.sprinboot.backend.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprinboot.backend.dto.UserEditDto;
import com.sprinboot.backend.dto.UserInfoDto;
import com.sprinboot.backend.dto.UserRegisterDto;
import com.sprinboot.backend.exceptions.InvalidEntryException;
import com.sprinboot.backend.model.UserProfile;
import com.sprinboot.backend.repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//Postman Post, should only be use for debug entry addition
	@PostMapping("/user-postman")
	public UserProfile postUserDebug(@RequestBody UserProfile u)
	{
		Optional<UserProfile> optional = userRepository.getByUsername(u.getUsername());
		
		if(optional.isPresent())
			throw new InvalidEntryException("[postUserDebug] Username already in use");
		
		u.setPasswordLastReset(LocalDate.now());
		u.setPassword(encoder.encode(u.getPassword()));
		return userRepository.save(u);
	}
	
	//Angular Post
	//Sign up
	/* 
	 @PostMapping("/user")
	public UserProfile postUser(@RequestBody UserRegisterDto dto)
	{
		String credentials = new String(Base64.getDecoder().decode(dto.getEncodedCredentials()));
		String username = credentials.split("---")[0],
			   password = credentials.split("---")[1];
		
		UserProfile u = new UserProfile(dto.getNickname(), username, encoder.encode(password), 
				dto.getRole(), dto.getSecurityAnswer(), dto.getSecurityQuestion(), 
				LocalDate.now());
		return u;
	}
	 * */
	
	
	//Angular post test
	@PostMapping("/user")
	public void postUser(@RequestBody UserRegisterDto dto) {
		String credentials = new String(Base64.getDecoder().decode(dto.getEncodedCredentials()));
		String username = credentials.split("---")[0];
		String password = credentials.split("---")[1];
		
		UserProfile u = new UserProfile();
		u.setNickname(dto.getNickname());
		u.setUsername(username);
		u.setPassword(encoder.encode(password));
		u.setSecurityQuestion(dto.getSecurityQuestion());
		u.setSecurityAnswer(dto.getSecurityAnswer());
		u.setRole(dto.getRole());
		u.setPasswordLastReset(LocalDate.now());
		
		userRepository.save(u);
		
	}
	
	//User get info about their account
	@GetMapping("/user/username")
	public UserEditDto getUserByUsername(Principal principal)
	{
		Optional<UserProfile> optional = userRepository.getByUsername(principal.getName());
		if(!optional.isPresent())
			throw new InvalidEntryException("[getUserbyUsername] Username does not exist");
		
		UserProfile u = optional.get();
		return new UserEditDto(u.getNickname(), u.getSecurityAnswer(), u.getSecurityQuestion());
	}
	
	@GetMapping("/user")
	public List<UserProfile> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	//Login for Angular
	@GetMapping("/login")
	public UserInfoDto login(Principal principal)
	{
		Optional<UserProfile> optional = userRepository.getByUsername(principal.getName());
		if(!optional.isPresent())
			throw new InvalidEntryException("[login] Username does not exist");
		
		UserProfile u = optional.get();
		return new UserInfoDto(u.getId(), u.getRole(), u.getNickname(), u.getUsername());
	}

	//Update User profile
	@PutMapping("/profile/edit")
	public void editProfile(Principal principal, @RequestBody UserRegisterDto dto)
	{
		Optional<UserProfile> optional = userRepository.getByUsername(principal.getName());
		if(!optional.isPresent())
			throw new InvalidEntryException("[editProfile] Username does not exist");
		
		userRepository.updateProfile(principal.getName(), dto.getNickname(), dto.getSecurityAnswer(), dto.getSecurityQuestion());
	}
	
	@GetMapping("/verify/{encodedText}")
	public boolean verifySecurityQuestion(@PathVariable("encodedText") String encodedText) 
	{
		String credentials = new String(Base64.getDecoder().decode(encodedText));
		String username = credentials.split("---")[0],
			   answer = credentials.split("---")[1];
		
		Optional<UserProfile> optional = userRepository.getByUsername(username);
		if(!optional.isPresent())
			throw new InvalidEntryException("[verifySecurtyQuestion] Username does not exist");
		UserProfile info = optional.get();
		
		if(info.getSecurityAnswer().equalsIgnoreCase(answer))
			return true;
		return false;
	}
	
	@PutMapping("/user/reset-password/{encodedText}")
	public void resetPassword(@PathVariable("encodedText") String encodedText) 
	{
		String credentials = new String(Base64.getDecoder().decode(encodedText));
		String username = credentials.split("---")[0],
			   password = credentials.split("---")[1];
		
		Optional<UserProfile> optional = userRepository.getByUsername(username);
		if(!optional.isPresent())
			throw new InvalidEntryException("[resetPassword] Username does not exist");

		userRepository.resetPassword(username, encoder.encode(password), LocalDate.now());
	}
}
