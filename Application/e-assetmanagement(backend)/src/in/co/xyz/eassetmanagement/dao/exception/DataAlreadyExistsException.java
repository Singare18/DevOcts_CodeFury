package in.co.xyz.eassetmanagement.dao.exception;

public class DataAlreadyExistsException extends Exception{

	private String message;

	public DataAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "DataAlreadyExistsException [message=" + message + "]";
	}
	
	
}
