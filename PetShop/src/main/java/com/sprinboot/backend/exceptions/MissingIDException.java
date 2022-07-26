/**
* An exception that is thrown when an ID can't be found in the DB
*
* @author  Felix Taylor
* @since   2022-07-25
*/
package com.sprinboot.backend.exceptions;

public class MissingIDException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message; // Exception reason
	
	//********** CONSTRUCTORS **********/
	
	public MissingIDException() {
		super();
	}
	public MissingIDException(String message) {
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
