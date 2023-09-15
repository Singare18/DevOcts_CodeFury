package in.co.xyz.eassetmanagement.dao.exception;

//This is a custom exception which we tell that the datastore is full and now no other element can be inseted
public class DataStoreFullException extends Exception{

	private String message;

	public DataStoreFullException() {
		super();
		this.message = "DataStore is Full";
	}

	public DataStoreFullException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString()
	{
		return message;
	}
	
}
