package in.co.xyz.eassetmanagement.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import in.co.xyz.eassetmanagement.exception.*;
import in.co.xyz.eassetmanagement.dao.BorrowerDao;
import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.InvalidCredentialsException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.Asset;
import in.co.xyz.eassetmanagement.model.Borrower;
import in.co.xyz.eassetmanagement.model.Transaction;
import in.co.xyz.eassetmanagement.service.AssetService;
import in.co.xyz.eassetmanagement.service.BorrowerService;
import in.co.xyz.eassetmanagement.util.Util;

public class BorrowerServiceImpl implements BorrowerService{

	//Coding to interfaces
		private BorrowerDao dao;
		
		private AssetService service;
		
		//DAO object is passed vai constructor
		//To avoid any tight coupling with any implementation of DAO
		public BorrowerServiceImpl(BorrowerDao dao) {
			this.dao = dao;
		}
	
	@Override
	public int save(Borrower borrower) throws DataStoreFullException, DataAlreadyExistsException {
		return this.dao.save(borrower);
	}

	@Override
	public Borrower findByBorrowerEmail(Borrower b) throws RecordNotFoundException {
		return this.dao.findByBorrowerEmail(b);
	}

	@Override
	public List<Borrower> findAllBorrowers() throws SQLException {
		return this.dao.findAllBorrowers();
	}

	@Override
	public Borrower updateBorrower(Borrower borrower) throws RecordNotFoundException {
		return this.dao.updateBorrower(borrower);
	}

	@Override
	public boolean deleteByBorrowerEmail(String email) throws RecordNotFoundException {
		return this.dao.deleteByBorrowerEmail(email);
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	//Business method 
	
	//method to search for the availability of asset and also checks if the borrower can actually
	@Override
	public boolean checksAssetAvailability(Asset asset,Borrower borrower) throws AssetNotFoundException, NotEligibleForAssetBorrowing {
		
		if(isBorrower(borrower)) {
			//Checks two conditions if the asset is available or not and if the borrower can borrow it or not
			//means it the borrower has previously not returned the asset on time then he cannot borrow any asset further.
			if(asset.isAvailable() && canBorrowAssets(asset,borrower)) {
				asset.setAvailable(false);
				asset.setCurrentBorrower(borrower.getCurrentBorrower());
				//to set the due date we will be calculating the currentDate + maxPeriod of the assetType
				//since it is in int datatype we have to convert it into Date format
				asset.setDueDate(Util.intToDateConversion(new Date().getDate() + asset.getAssetType().getMaxPeriod()));
				borrower.getListOfAssestBorrowerBorrowed().add(asset);//add the asset to the list of assests a borrower borrowed
				return true; //Confirms that the borrower has successfully borrowed the asset
			}
		}
		throw new AssetNotFoundException("Sorry this asset is not available");
	}
	
	@Override
	//method to check whether the borrower can actually borrow the asset or not
	public boolean canBorrowAssets(Asset asset,Borrower borrower) throws NotEligibleForAssetBorrowing {
		if(isBorrower(borrower)) {
			List<Transaction> transactions = asset.getTransactions();
			for(Transaction t : transactions) {
				//If from the list of transaction any transaction's returndate is is > duedate it means borrower
				//has not been returned the asset in the specified time hence he is not eligible of getting 
				//any further asset
				if(t.getAssetReturnDate().getDate() > asset.getDueDate().getDate()) {
					throw new NotEligibleForAssetBorrowing("Sorry but you are not eligible to borrow any asset now");
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void returnAsset(Asset asset,Borrower borrower) {
		if(isBorrower(borrower)) {
			List<Asset> assets = borrower.getListOfAssestBorrowerBorrowed();
			if(assets.contains(asset)) {
				asset.setCurrentBorrower(null);//now there is no current borrower
				asset.setDueDate(null); //no due date now
				assets.remove(asset);
				double fine = service.fineImposed(asset); 
				borrower.totalFine(fine); //represents the total fine a borrower is having
			}
		}
	}

	//This method checks if the user is a borrower or not if not then he is not able to access any asset
	public boolean isBorrower(Borrower borrower) {
		if(borrower.getUserType().equalsIgnoreCase("Borrower")) return true;
		return false;
	}

	@Override
	public boolean isLoginSuccess(Borrower b) throws InvalidCredentialsException {
	      return this.dao.isLoginSuccess(b);
	}
	
	
	
	}
	

