package com.exceptions;

public class LoginFailedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public LoginFailedException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

}
