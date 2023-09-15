package in.co.xyz.eassetmanagement.service;

import java.sql.SQLException;
import java.util.List;

import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.Asset;

public interface AssetService extends AutoCloseable{

	int save(Asset asset) throws DataStoreFullException, DataAlreadyExistsException;
	Asset findByAssetId(String assetName) throws RecordNotFoundException;
	List<Asset> findAllAssets() throws SQLException;
	Asset updateAsset(Asset asset) throws RecordNotFoundException;
	boolean deleteByAssetId(String assetName) throws RecordNotFoundException;
	void close() throws Exception;
	
	//Business method
	public double fineImposed(Asset asset);
}
