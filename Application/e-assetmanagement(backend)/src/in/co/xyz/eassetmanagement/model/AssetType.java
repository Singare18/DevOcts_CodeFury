package in.co.xyz.eassetmanagement.model;

//This class will specify the different categories of assets
public class AssetType {

	
	private String assetTypeName;
	private Double fineAmount; //Every asset has its own specific fine amount
	private int maxPeriod; //represents the maximum period for a borrower to borrow the specific asset.
	
	//Non-parameterised constructor
	public AssetType() {
		super();
	}

	//parameterised constructor
	public AssetType(String assetTypeName, Double fineAmount, int maxPeriod) {
		super();
		this.assetTypeName = assetTypeName;
		this.fineAmount = fineAmount;
		this.maxPeriod = maxPeriod;
	}

	
	//Getter and Setter methods
	//since we have made our attributes as private so to access them we'll be using the public methods
	//in this way we are implementing the core concept of OOPS ie Encapsulation and Abstraction
	public String getAssetTypeName() {
		return assetTypeName;
	}

	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}

	public Double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public int getMaxPeriod() {
		return maxPeriod;
	}

	public void setMaxPeriod(int maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	//This is a call - back function which is overridden from Object class
	@Override
	public String toString() {
		return "AssetType [assetTypeName=" + assetTypeName + ", fineAmount=" + fineAmount + ", maxPeriod=" + maxPeriod
				+ "]";
	}
	
	
	
	
}
