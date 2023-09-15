package in.co.xyz.eassetmanagement.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import in.co.xyz.eassetmanagement.dao.AssetDao;
import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.Asset;
import in.co.xyz.eassetmanagement.service.AssetService;

public class AssetServiceImpl implements AssetService{

	//Coding to interfaces
			private AssetDao dao;
			
			//DAO object is passed vai constructor
			//To avoid any tight coupling with any implementation of DAO
			public AssetServiceImpl(AssetDao dao) {
				this.dao = dao;
			}
	
	@Override
	public int save(Asset asset) throws DataStoreFullException, DataAlreadyExistsException {
		return this.dao.save(asset);
	}

	@Override
	public Asset findByAssetId(String assetName) throws RecordNotFoundException {
		return this.dao.findByAssetId(assetName);
	}

	@Override
	public List<Asset> findAllAssets() throws SQLException {
		return this.dao.findAllAssets();
	}

	@Override
	public Asset updateAsset(Asset asset) throws RecordNotFoundException {
		return this.dao.updateAsset(asset);
	}

	@Override
	public boolean deleteByAssetId(String assetName) throws RecordNotFoundException {
		return this.dao.deleteByAssetId(assetName);
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	//method used to calculate the total fine amount imposed to the borrower
	public double fineImposed(Asset asset) {
		double lateFineAmount = 0;
		//checks if the dueDate is not null and the date in which borrower returns the asset is the date after
		//the dueDate is true then the borrower is eligible for latefine
		if(asset.getDueDate() != null && asset.getDueDate().after(new Date())) {
			//total no. of days late will be calculated by currentDate - dueDate
			int daysLate = new Date().getDate() - asset.getDueDate().getDate();
			
			//and if this daysLate is > than the max period of asset then late fine will be imposed else
			//no fine will be inposed
			if(daysLate > asset.getAssetType().getMaxPeriod()) {
				lateFineAmount = (asset.getAssetType().getMaxPeriod() - daysLate) * asset.getAssetType().getFineAmount();
			}
		}
		return lateFineAmount;
	}

}
