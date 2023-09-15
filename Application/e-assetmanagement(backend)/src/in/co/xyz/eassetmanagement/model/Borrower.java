package in.co.xyz.eassetmanagement.model;

import java.util.ArrayList;
import java.util.List;

import in.co.xyz.eassetmanagement.enums.*;
public class Borrower {

	private int id;
	private String borrowerName;
	private long phoneNo;
	private String email;
	private String password;
	private UserType userType; //Since we have specific usertype so I am taking usertype as enum which has predefined values
	private List<Asset> listOfAssestBorrowerBorrowed;
	private double totalFine;
	
	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Borrower(int id, String borrowerName, long phoneNo, String email, String password, String userType) {
		super();
		this.id = id;
		this.borrowerName = borrowerName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.userType = UserType.valueOf(userType.toUpperCase());
		this.listOfAssestBorrowerBorrowed = new ArrayList<Asset>();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType.name();
	}
	public void setUserType(String userType) {
		this.userType = UserType.valueOf(userType.toUpperCase());
	}
	public List<Asset> getListOfAssestBorrowerBorrowed() {
		return listOfAssestBorrowerBorrowed;
	}
	public void setListOfAssestBorrowerBorrowed(List<Asset> listOfAssestBorrowerBorrowed) {
		this.listOfAssestBorrowerBorrowed = listOfAssestBorrowerBorrowed;
	}
	
	//specifies the totalFine the borrower has to pay
	public void totalFine(double totalFine) {
		this.totalFine += totalFine;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Borrower [id=" + id + ", borrowerName=" + borrowerName + ", phoneNo=" + phoneNo + ", email=" + email
				+ ", password=" + password + ", userType=" + userType + ", listOfAssestBorrowerBorrowed="
				+ listOfAssestBorrowerBorrowed + ", totalFine=" + totalFine + "]";
	}
	//Business methods
	public  Borrower getCurrentBorrower() {
		return this;
	}
	
	public List<Asset> listOfAssets(){
		return this.listOfAssestBorrowerBorrowed;
	}
	
	
}
