package in.co.xyz.eassetmanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import in.co.xyz.eassetmanagement.dal.JdbcConnection;
import in.co.xyz.eassetmanagement.dao.AssetDao;
import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.Asset;


public class AssetDaoImpl implements AssetDao{

	//We are directly calling the static method from the class name itself and using it in every CRUD operation
	 private Connection con;
	    public AssetDaoImpl() {
	    	this.con = JdbcConnection.openConnection();
	    }
	    
	//to save the asset
	@Override
	public int save(Asset asset) throws DataStoreFullException, DataAlreadyExistsException {
		String query = "INSERT INTO asset_tbl VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps,ps1;
		try {
			ps = con.prepareStatement(query);
			
			ps1 = con.prepareStatement("SELECT * FROM asset_tbl WHERE assetName = ?");
			ps1.setString(1,asset.getAssetName());
			//Checks if the asset is already present in the database or not
			//Here I am checking the assetName if it is present or not as id will be autogenerated so
			//it will always be unique so we cant compare based on id.
			ResultSet resultSet = ps1.executeQuery();
			if(resultSet.next()) {
				throw new DataAlreadyExistsException("Already Present.Try another one");
			}
			
			ps.setInt(1, asset.getId());
			ps.setString(2, asset.getAssetName());
			ps.setBoolean(3,asset.isAvailable());
			ps.setString(4,asset.getDescription());
			java.sql.Date dueDate = new Date(asset.getDueDate().getTime());
			ps.setDate(5,dueDate);
			ps.setString(6,asset.getAssetType().getAssetTypeName());
			ps.setInt(7,asset.getCurrentBorrower().getId());
			int numberOfRowsUpdated = ps.executeUpdate(); //query is getting executed
			if(numberOfRowsUpdated == 1) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	//to find by particular asset
	@Override
	public Asset findByAssetId(String assetName) throws RecordNotFoundException {
		Asset asset = new Asset();
		String query = "SELECT * FROM asset_tbl WHERE id = ?";
		PreparedStatement ps = null;
		try {
			if(isAssetPresent(assetName)) {
			ps = con.prepareStatement(query);
			ps.setString(1,assetName);
			ResultSet rs = ps.executeQuery();
		      while(rs.next()) {
		        if(rs.getString(1).equals(assetName)) {
		        	asset.setId((int)Math.random()); //random id is generated but the comparsion is always based in assetNames
		        	asset.setAssetName(rs.getString(2));
		        	asset.setAvailable(rs.getBoolean(3));
		        	asset.setDescription(rs.getString(4));
		        	asset.setDueDate(rs.getDate(5));
		        	return asset;
		        }
		      }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	   return null;
	}

	//to find all the assets
	@Override
	public List<Asset> findAllAssets() throws SQLException {
		String query = "SELECT * FROM asset_tbl";
		 List<Asset> assets = new ArrayList<Asset>();
		try{
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
		      while(rs.next()) {
		    	  Asset asset = new Asset(rs.getInt(1),rs.getString(2),rs.getBoolean(3),rs.getString(4),rs.getDate(5),null,null);
		    	  assets.add(asset);
	            }
		      return assets;
		}catch (SQLException e) {
			e.printStackTrace();
			//This is called partial handling
			throw e; //this is rethrowing the exception
		}
	}

	//To update a particular asset
	@Override
	public Asset updateAsset(Asset asset) throws RecordNotFoundException {
		String query = "UPDATE asset_tbl SET assetName = ?,description = ?,isAvailable = ?"
				+ ",dueDate = ? WHERE id = ?";
		try {
			if(isAssetPresent(asset.getAssetName())) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, asset.getAssetName());
			ps.setString(2,asset.getDescription());
			ps.setBoolean(3,asset.isAvailable());
			java.sql.Date dueDate = new Date(asset.getDueDate().getTime());
			ps.setDate(4,dueDate);
			ps.setInt(5,asset.getId());
			//here I am not mentioning the transaction part to be updated 
			//because it wont be done by the admin itself
        	//It will be modified by the borrower and asset table both
			int rowsAffected = ps.executeUpdate(); //query is getting executed
			if(rowsAffected == 1) {
				return asset;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	//Deletes the particular asset
	@Override
	public boolean deleteByAssetId(String assetName) throws RecordNotFoundException {
		String query = "DELETE FROM asset_tbl WHERE id = ?";
		try {
			if(isAssetPresent(assetName)) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,assetName);
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

	//checks if asset already present
	public boolean isAssetPresent(String assetName) throws RecordNotFoundException {
		PreparedStatement ps1;
		try {
			ps1 = con.prepareStatement("SELECT * FROM asset_tbl WHERE assetName = ?");
			ps1.setString(1,assetName);
			
			//Checks if the record is already present or not.
			ResultSet rs = ps1.executeQuery();
			if(rs.next() == false) {
				throw new RecordNotFoundException("This record is not present.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
