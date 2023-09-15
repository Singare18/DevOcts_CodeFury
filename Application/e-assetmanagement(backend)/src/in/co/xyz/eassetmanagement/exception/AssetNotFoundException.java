package in.co.xyz.eassetmanagement.exception;

public class AssetNotFoundException extends Exception{

	private String message;

	public AssetNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "AssetNotFoundException [message=" + message + "]";
	}
	
}
