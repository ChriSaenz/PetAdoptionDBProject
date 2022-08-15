// Simple Entry Exception to specify error type

package com.sprinboot.backend.exceptions;

public class InvalidEntryException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message; // Exception reason
	
	//********** CONSTRUCTORS **********/
	
	public InvalidEntryException() {
		super();
	}
	public InvalidEntryException(String message) {
		super();
		this.message = message;
	}
	
	//********** GETTERS & SETTERS **********/
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	//********** TOSTRING **********//
	
	@Override
	public String toString() {
		return "MissingIDException [message=" + message + "]";
	}
}
