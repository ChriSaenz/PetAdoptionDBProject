package com.objects;

import com.main.Type;

public class User {
	
	int id;
	String username;
	String password;
	Type type;
	public User(String username, String password, Type type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password
				+ ", type=" + type + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

}
