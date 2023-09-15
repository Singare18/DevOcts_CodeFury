package in.co.xyz.eassetmanagement.service;

import java.sql.SQLException;
import java.util.List;

import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.InvalidCredentialsException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.exception.AssetNotFoundException;
import in.co.xyz.eassetmanagement.exception.NotEligibleForAssetBorrowing;
import in.co.xyz.eassetmanagement.model.Asset;
import in.co.xyz.eassetmanagement.model.Borrower;

public interface BorrowerService extends AutoCloseable{
	
	int save(Borrower borrower) throws DataStoreFullException, DataAlreadyExistsException;
	Borrower findByBorrowerEmail(Borrower b) throws RecordNotFoundException;
	List<Borrower> findAllBorrowers() throws SQLException;
	Borrower updateBorrower(Borrower borrower) throws RecordNotFoundException;
	boolean deleteByBorrowerEmail(String email) throws RecordNotFoundException;
	void close() throws Exception;
	
	//Business methods
	boolean checksAssetAvailability(Asset asset,Borrower borrower) throws AssetNotFoundException, NotEligibleForAssetBorrowing;
	public boolean canBorrowAssets(Asset asset,Borrower borrower) throws NotEligibleForAssetBorrowing;
	public void returnAsset(Asset asset,Borrower borrower);
	public boolean isLoginSuccess(Borrower b) throws InvalidCredentialsException;
	
	
}
