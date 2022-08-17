// Dto used mostly for Security Questions reset

package com.sprinboot.backend.dto;

public class UserEditDto {
	private String nickname;
	private String securityAnswer;
	private String securityQuestion;
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

	public UserEditDto(String nickname, String securityAnswer, String securityQuestion) {
		super();
		this.nickname = nickname;
		this.securityAnswer = securityAnswer;
		this.securityQuestion = securityQuestion;
	}
	@Override
	public String toString() {
		return "UserEditDto [nickname=" + nickname + ", securityAnswer=" + securityAnswer + ", securityQuestion="
				+ securityQuestion + "]";
	}
	
	
}
