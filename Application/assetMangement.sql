CREATE TABLE 'e_assetmanagement_db'.'asset_tbl' (
  'asset_id' INT NOT NULL AUTO_INCREMENT,
  'assetName' VARCHAR(45) NOT NULL,
  'isAvailable' TINYINT NOT NULL,
  'description' VARCHAR(45) NOT NULL,
  'dueDate' DATE NOT NULL,
  'currentBorrowerId' INT NULL,
  PRIMARY KEY ('asset_id'),
  INDEX 'assetType_idx' ('assetName' ASC) VISIBLE,
  INDEX 'currnetBorrower_idx' ('currentBorrowerId' ASC) VISIBLE,
  CONSTRAINT 'assetType'
    FOREIGN KEY ('assetName')
    REFERENCES 'e_assetmanagement_db'.'assettype_tbl' ('assetTypeName')
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT 'currnetBorrower'
    FOREIGN KEY ('currentBorrowerId')
    REFERENCES 'e_assetmanagement_db'.'borrower_tbl' ('borrower_id')
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE 'e_assetmanagement_db'.'borrower_tbl' (
  'borrower_id' INT NOT NULL,
  'borrowerName' VARCHAR(45) NOT NULL,
  'phoneNo' BIGINT NOT NULL,
  'email' VARCHAR(45) NOT NULL,
  'password' VARCHAR(45) NOT NULL,
  'userType' VARCHAR(45) NOT NULL,
  PRIMARY KEY ('borrower_id'));

CREATE TABLE 'e_assetmanagement_db'.'transaction_tbl' (
  'transaction_id' INT NOT NULL AUTO_INCREMENT,
  'asset' INT NULL,
  'borrower' INT NULL,
  'borrowedDate' DATE NULL,
  'returnedDate' DATE NULL,
  'fine' DOUBLE NULL,
  PRIMARY KEY ('transaction_id'),
  INDEX 'asset_idx' ('asset' ASC) VISIBLE,
  INDEX 'borrower_idx' ('borrower' ASC) VISIBLE,
  CONSTRAINT 'asset'
    FOREIGN KEY ('asset')
    REFERENCES 'e_assetmanagement_db'.'asset_tbl' ('asset_id')
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT 'borrower'
    FOREIGN KEY ('borrower')
    REFERENCES 'e_assetmanagement_db'.'borrower_tbl' ('borrower_id')
    ON DELETE CASCADE
    ON UPDATE CASCADE);


CREATE TABLE 'e_assetmanagement_db'.'assettype_tbl' (
  'fine' DOUBLE NOT NULL,
  'maxPeriod' INT NOT NULL,
  'assetTypeName' VARCHAR(45) NOT NULL,
  PRIMARY KEY ('assetTypeName'));




INSERT INTO 'e_assetmanagement_db'.'assest_tbl' ('assetId', 'assetName', 'isAvailable', 'description', 'dateAdded', 'assetType') VALUES ('3', 'MacBook', true, 'Working', '2022-12-11', 'Laptop');





