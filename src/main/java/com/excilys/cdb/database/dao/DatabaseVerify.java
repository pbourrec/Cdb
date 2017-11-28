package com.excilys.cdb.database.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.dao.jpadata.CompanyRepository;
import com.excilys.cdb.database.dao.jpadata.ComputerRepository;

@Component
public class DatabaseVerify {
	Logger logger = LoggerFactory.getLogger(DatabaseVerify.class);
	@Autowired
	private ComputerRepository computerJpaDao;
	
	@Autowired
	private CompanyRepository companyJpaDao;

	/**
	 * 
	 * @param stmt Statement
	 * @param computerManufacturer ID du fabricant voulu
	 * @return boolean isOK le boolean sera vrai si la table "company" contient bien l'ID du fabricant voulu
	 */
	public  boolean isIdOkCompany(Long companyId){
		boolean isOK = false;
		if(companyId != null){
			Company company= companyJpaDao.getOne(companyId);
			if(company!=null){
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
			int sizeTable = computerJpaDao.getSizeComputerIdJpa(computerId);
			if(sizeTable==1){
				isOK = true;
			}
		}else{
			isOK=true;
		}
		return isOK;
	}

}
