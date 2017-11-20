package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseVerify {
	Logger logger = LoggerFactory.getLogger(DatabaseVerify.class);
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ComputerDAO computerDao;
	/**
	 * 
	 * @param stmt Statement
	 * @param computerManufacturer ID du fabricant voulu
	 * @return boolean isOK le boolean sera vrai si la table "company" contient bien l'ID du fabricant voulu
	 */
	public  boolean isIdOkCompany(Long companyId){
		boolean isOK = false;
		if(companyId != null){
			int sizeTable = companyDao.getSizeCompanyId(companyId);
			if(sizeTable==1){
				isOK = true;
			}
		}else{
			isOK=true;
		}
		return isOK;
	}

	/**
	 * 
	 * @param stmt Statement
	 * @param computer ID ID de l'ordinateur voulu
	 * @return boolean isOK le boolean sera vrai si la table "computer" contient bien l'ID de l'ordinateur voulu
	 */
	public  boolean isIdOkComputer(Long computerId){
		boolean isOK = false;
		if(computerId != null){
			int sizeTable = computerDao.getSizeComputerId(computerId);
			if(sizeTable==1){
				isOK = true;
			}
		}else{
			isOK=true;
		}
		return isOK;
	}

}
