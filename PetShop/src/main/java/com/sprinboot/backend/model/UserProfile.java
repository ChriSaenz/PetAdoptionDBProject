package com.sprinboot.backend.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Use this class for front-end login and other components
 */
@Entity
@Table(name = "user")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private String securityQuestion;
	
	@Column(nullable = false)
	private String securityAnswer;
	
	private LocalDate passwordLastReset;
	
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserProfile(String nickname, String username, String password, String role, String securityQuestion,
			String securityAnswer, LocalDate passwordLastReset) {
		super();
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.passwordLastReset = passwordLastReset;
	}



	public UserProfile(Long id, String nickname, String username, String password, String role, String securityQuestion,
			String securityAnswer, LocalDate passwordLastReset) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.passwordLastReset = passwordLastReset;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public LocalDate getPasswordLastReset() {
		return passwordLastReset;
	}
	public void setPasswordLastReset(LocalDate passwordLastReset) {
		this.passwordLastReset = passwordLastReset;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", role=" + role + ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer
				+ ", passwordLastReset=" + passwordLastReset + "]";
	}
	
}
