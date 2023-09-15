package in.co.xyz.eassetmanagement.model;

import java.util.Date;

//This class will keep track of the user and asset relationship
public class Transaction {

	private int id;
	private Asset asset;
	private Borrower borrower;
	private Date borrowedDate; 
	private Date assetReturnDate;
	private double fineAmount;
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(int id, Asset asset, Borrower borrower, Date borrowedDate, Date assetReturnDate,
			double fineAmount) {
		super();
		this.id = id;
		this.asset = asset;
		this.borrower = borrower;
		this.borrowedDate = borrowedDate;
		this.assetReturnDate = assetReturnDate;
		this.fineAmount = fineAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public Date getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	public Date getAssetReturnDate() {
		return assetReturnDate;
	}
	public void setAssetReturnDate(Date assetReturnDate) {
		this.assetReturnDate = assetReturnDate;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", asset=" + asset + ", borrower=" + borrower + ", borrowedDate="
				+ borrowedDate + ", assetReturnDate=" + assetReturnDate + ", fineAmount=" + fineAmount + "]";
	}
	
	
	
}
