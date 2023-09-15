package in.co.xyz.eassetmanagement.dao;
import java.sql.SQLException;
import java.util.List;

import in.co.xyz.eassetmanagement.dao.exception.DataAlreadyExistsException;
import in.co.xyz.eassetmanagement.dao.exception.DataStoreFullException;
import in.co.xyz.eassetmanagement.dao.exception.RecordNotFoundException;
import in.co.xyz.eassetmanagement.model.*;


//THIS IS A CONTRACT THAT DAO-IMPL CLASS MUST FOLLOW
public interface AssetDao extends AutoCloseable{
	int save(Asset asset) throws DataStoreFullException, DataAlreadyExistsException;
	Asset findByAssetId(String assetName) throws RecordNotFoundException;
	List<Asset> findAllAssets() throws SQLException;
	Asset updateAsset(Asset asset) throws RecordNotFoundException;
	boolean deleteByAssetId(String assetName) throws RecordNotFoundException;
	void close() throws Exception;
}
