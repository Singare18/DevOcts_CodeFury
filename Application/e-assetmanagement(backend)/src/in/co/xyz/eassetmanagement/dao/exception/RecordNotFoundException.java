package in.co.xyz.eassetmanagement.dao.exception;

//This is a custom exception which will tell that the particular record is no present in the datastore
public class RecordNotFoundException extends Exception{

	private String message;

	public RecordNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "RecordNotFoundException [message=" + message + "]";
	}

	
}
