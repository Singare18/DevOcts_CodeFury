package in.co.xyz.eassetmanagement.dao;

import java.sql.SQLException;
import java.util.List;

import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.InvalidCredentialsException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.*;

public interface BorrowerDao extends AutoCloseable{
	int save(Borrower borrower) throws DataStoreFullException, DataAlreadyExistsException;
	Borrower findByBorrowerEmail(Borrower b) throws RecordNotFoundException;
	List<Borrower> findAllBorrowers() throws SQLException;
	Borrower updateBorrower(Borrower borrower) throws RecordNotFoundException;
	boolean deleteByBorrowerEmail(String email) throws RecordNotFoundException;
	public boolean isLoginSuccess(Borrower b) throws InvalidCredentialsException ;
	void close() throws Exception;
}
