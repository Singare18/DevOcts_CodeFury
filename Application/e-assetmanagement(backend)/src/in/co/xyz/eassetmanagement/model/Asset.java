package in.co.xyz.eassetmanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Asset is a model or a business entity which will be a part of table in database also
public class Asset {

	
	//attributes of assets
	private int id;
	private String assetName;
	private boolean isAvailable;
	private String description;
	private Date dueDate;
	private AssetType assetType;
	private Borrower currentBorrower;
	private List<Transaction> transactions;
	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Asset(int id, String assetName, boolean isAvailable, String description, Date dueDate,AssetType assetType,Borrower currentBorrower) {
		super();
		this.id = id;
		this.assetName = assetName;
		this.isAvailable = isAvailable;
		this.description = description;
		this.dueDate = dueDate;
		this.assetType = assetType;
		this.currentBorrower = currentBorrower;
		this.transactions = new ArrayList<Transaction>();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public AssetType getAssetType() {
		return assetType;
	}
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public Borrower getCurrentBorrower() {
		return currentBorrower;
	}
	public void setCurrentBorrower(Borrower currentBorrower) {
		this.currentBorrower = currentBorrower;
	}
	@Override
	public String toString() {
		return "Asset [id=" + id + ", assetName=" + assetName + ", isAvailable=" + isAvailable + ", description="
				+ description + ", dueDate=" + dueDate + ", assetType=" + assetType + ", currentBorrower="
				+ currentBorrower + ", transactions=" + transactions + "]";
	}
	
	
	
	
} 
