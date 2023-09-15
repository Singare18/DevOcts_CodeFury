package in.co.xyz.eassetmanagement.exception;

public class NotEligibleForAssetBorrowing extends Exception{

	private String message;

	public NotEligibleForAssetBorrowing(String message) {
		super();
		this.message = message;
	}
	
	
}
