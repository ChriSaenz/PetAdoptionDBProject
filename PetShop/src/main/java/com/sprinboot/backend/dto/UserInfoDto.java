//Dto used for basic User information needed

package com.sprinboot.backend.dto;

public class UserInfoDto {
	private Long id;
	private String role;
	private String nickname;
	private String username;
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
	public UserInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfoDto(Long id, String role, String nickname, String username) {
		super();
		this.id = id;
		this.role = role;
		this.nickname = nickname;
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserInfoDto [id=" + id + ", role=" + role + ", nickname=" + nickname + ", username=" + username + "]";
	}

	
}
