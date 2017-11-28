package com.excilys.cdb.database.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.dao.jpadata.CompanyRepository;

@Service("CompanyService")
public class CompanyService {
	 Logger logger = LoggerFactory.getLogger(CompanyService.class);

	 @Autowired
	 private CompanyRepository companyJpaDao;

	/**
	 * 
	 * @param sc Scanner input
	 * @param rsComputer Resultat de la query
	 */
	public  void viewAllCompany(){
		//Demande du nombre d'ordinateurs par "page"
		List<Company> listCompany= companyJpaDao.findAll();

		for (Company comp : listCompany){
			System.out.println(comp.toString());
			System.out.println("----");

		}
	}
	public  void deleteCompany(Long companyToDelete) throws SQLException {
		companyJpaDao.deleteById(companyToDelete);;
	}
	public  List<Company> getListCompany(){
		List<Company> listCompany = companyJpaDao.findAll();
		return listCompany;
	}
}
