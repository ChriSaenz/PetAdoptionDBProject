package com.sprinboot.backend.dto;

public class UserRegisterDto {
	private String role;
	private String nickname;
	private String securityAnswer;
	private String securityQuestion;
	private String encodedCredentials;
	
	
	public UserRegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserRegisterDto(String role, String nickname, String securityAnswer, String securityQuestion,
			String encodedCredentials) {
		super();
		this.role = role;
		this.nickname = nickname;
		this.securityAnswer = securityAnswer;
		this.securityQuestion = securityQuestion;
		this.encodedCredentials = encodedCredentials;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getSecurityAnswer() {
		return securityAnswer;
	}


	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}


	public String getSecurityQuestion() {
		return securityQuestion;
	}


	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}


	public String getEncodedCredentials() {
		return encodedCredentials;
	}


	public void setEncodedCredentials(String encodedCredentials) {
		this.encodedCredentials = encodedCredentials;
	}


	@Override
	public String toString() {
		return "UserRegisterDto [role=" + role + ", nickname=" + nickname + ", securityAnswer=" + securityAnswer
				+ ", securityQuestion=" + securityQuestion + ", encodedCredentials=" + encodedCredentials + "]";
	}
	
}
