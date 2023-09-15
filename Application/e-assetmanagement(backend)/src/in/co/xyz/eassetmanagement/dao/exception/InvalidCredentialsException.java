package in.co.xyz.eassetmanagement.dao.exception;

public class InvalidCredentialsException extends Exception{

	private String message;

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}
	
	
}
