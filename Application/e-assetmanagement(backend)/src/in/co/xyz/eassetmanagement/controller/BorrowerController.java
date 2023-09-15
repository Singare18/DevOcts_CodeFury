package in.co.xyz.eassetmanagement.controller;

import in.co.xyz.eassetmanagement.dao.impl.BorrowerDaoImpl;
import in.co.xyz.eassetmanagement.exception.AssetNotFoundException;
import in.co.xyz.eassetmanagement.exception.NotEligibleForAssetBorrowing;
import in.co.xyz.eassetmanagement.model.Asset;
import in.co.xyz.eassetmanagement.model.AssetType;
import in.co.xyz.eassetmanagement.model.Borrower;

import java.sql.SQLException;
import java.util.Date;

import in.co.xyz.eassetmanagement.dao.*;
import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.InvalidCredentialsException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.service.*;
import in.co.xyz.eassetmanagement.service.impl.*;
public class BorrowerController {

	public static void main(String[] args) throws DataStoreFullException, DataAlreadyExistsException, SQLException, RecordNotFoundException, AssetNotFoundException, NotEligibleForAssetBorrowing, InvalidCredentialsException {
	
		 BorrowerDao dao = new BorrowerDaoImpl();
		 BorrowerService service = new BorrowerServiceImpl(dao);
		 
		 Borrower b = new Borrower(4,"ABC",679012567,"yashwanth@gmail.com","yashwanth1","BORROWER");
         System.out.println(service.isLoginSuccess(b));
	}

}
