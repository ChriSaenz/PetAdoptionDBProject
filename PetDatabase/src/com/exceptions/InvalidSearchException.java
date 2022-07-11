package src.com.exceptions;

public class InvalidSearchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InvalidSearchException(String message) {
		super();
		this.message = message;
	}

}