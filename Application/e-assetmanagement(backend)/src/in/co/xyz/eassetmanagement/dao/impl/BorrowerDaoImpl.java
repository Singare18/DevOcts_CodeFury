package in.co.xyz.eassetmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.xyz.eassetmanagement.dal.JdbcConnection;
import in.co.xyz.eassetmanagement.dao.BorrowerDao;
import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.InvalidCredentialsException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.Asset;
import in.co.xyz.eassetmanagement.model.Borrower;

public class BorrowerDaoImpl implements BorrowerDao{

	//We are directly calling the static method from the class name itself and using it in every CRUD operation
		 private Connection con;
		    public BorrowerDaoImpl() {
		    	this.con = JdbcConnection.openConnection();
		    }
		    
	private List<Asset> assets;
	
	//To save the borrower in the database
	@Override
	public int save(Borrower borrower) throws DataStoreFullException, DataAlreadyExistsException {
		String query = "INSERT INTO borrower_tbl VALUES(?,?,?,?,?,?)";
		PreparedStatement ps,ps1;
		try {
			ps = con.prepareStatement(query);
			ps1 = con.prepareStatement("SELECT * FROM borrower_tbl WHERE email = ?");
			ps1.setString(1,borrower.getEmail());
			//Checks if the borrower is already present in the database or not
			//Here I am checking the email if it is present or not as id will be autogenerated so
			//it will always be unique so we cant compare based on id.
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next()) {
				throw new DataAlreadyExistsException("Already Present.Try another one");
			}
			
			ps.setInt(1, borrower.getId());
			ps.setString(2, borrower.getBorrowerName());
			ps.setLong(3,borrower.getPhoneNo());
			ps.setString(4,borrower.getEmail());
			ps.setString(5,borrower.getPassword());
			ps.setString(6,borrower.getUserType());
			
			int numberOfRowsUpdated = ps.executeUpdate(); //query is getting executed
			if(numberOfRowsUpdated == 1) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	//To find the particular borrower
	@Override
	public Borrower findByBorrowerEmail(Borrower b) throws RecordNotFoundException {
		Borrower borrower = new Borrower();
		String query = "SELECT * FROM borrower_tbl WHERE email = ?";
		PreparedStatement ps = null;
		try {
			if(isBorrowerPresent(b.getEmail())) {
			ps = con.prepareStatement(query);
			ps.setString(1,b.getEmail());
			ResultSet rs = ps.executeQuery();
		      while(rs.next()) {
		        if(rs.getString(4).equals(b.getEmail())) {
		        	borrower.setId(b.getId()); //random id is generated but the comparsion is always based in emails
		        	borrower.setBorrowerName(rs.getString(2));
		        	borrower.setPhoneNo(rs.getLong(3));
		        	borrower.setEmail(rs.getString(4));
		        	borrower.setPassword(rs.getString(5));
		        	borrower.setUserType(rs.getString(6));
		        	return borrower;
		        }
		      }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//To find all the borrowers
	@Override
	public List<Borrower> findAllBorrowers() throws SQLException {
		String query = "SELECT * FROM borrower_tbl";
		 List<Borrower> borrowers = new ArrayList<Borrower>();
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
		      while(rs.next()) {
		    	  Borrower borrower = new Borrower(rs.getInt(1),rs.getString(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6));
		    	  borrowers.add(borrower);
	            }
		      return borrowers;
		}catch (SQLException e) {
			e.printStackTrace();
			//This is called partial handling
			throw e; //this is rethrowing the exception
		}
	}

	//Update will be done using email as id is auto generated
	@Override
	public Borrower updateBorrower(Borrower borrower) throws RecordNotFoundException {
		String query = "UPDATE borrower_tbl SET borrowerName = ?, phoneNo = ?,password = ?"
				+ " WHERE email = ?";
		try {
			if(isBorrowerPresent(borrower.getEmail())) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, borrower.getBorrowerName());
			ps.setLong(2,borrower.getPhoneNo());
			ps.setString(3,borrower.getPassword());
			ps.setString(4,borrower.getEmail());
			//here I am not mentioning the Asset part to be updated 
			//because it wont be done by the user itself
        	//It will be modified by the activities(return borrowered asset on time) and also role of the borrower will not be changed
			int rowsAffected = ps.executeUpdate(); //query is getting executed
			if(rowsAffected == 1) {
				return borrower;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//Deletes the borrower with the email only as id is auto generated
	@Override
	public boolean deleteByBorrowerEmail(String email) throws RecordNotFoundException {
	
		String query = "DELETE FROM borrower_tbl WHERE email = ?";
		try {
			if(isBorrowerPresent(email)) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,email);
			int affectedrows = ps.executeUpdate();
			if(affectedrows == 1) {
				return true;
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void close() throws Exception {
	}
	
	//to check if borrower is already present
	public boolean isBorrowerPresent(String email) throws RecordNotFoundException {
		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement("SELECT * FROM borrower_tbl WHERE email = ?");
			ps1.setString(1,email);
			//Checks if the record is already present or not.
			ResultSet rs = ps1.executeQuery();
			if(rs.next() == false) {
				throw new RecordNotFoundException("This record is not present.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	//Checks if login credentials matches or not
	public boolean isLoginSuccess(Borrower b) throws InvalidCredentialsException {
		String query = "SELECT email,password FROM borrower_tbl";
		try {
			PreparedStatement ps = con.prepareStatement(query);
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(b.getEmail()) && rs.getString(2).equals(b.getPassword())) {
					return true;
				}
			}
		}catch(Exception e) {
			
		}
		throw new InvalidCredentialsException("Invalid Credentials. Please provide correct credentials");
	}
}
