package com.excilys.cdb.database.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;

@Service
public class CompanyService {
	 Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	 @Autowired
	 private CompanyDAO companyDAO;

	/**
	 * 
	 * @param sc Scanner input
	 * @param rsComputer Resultat de la query
	 */
	public  void viewAllCompany(){
		//Demande du nombre d'ordinateurs par "page"
		List<Company> listCompany= companyDAO.getCompany();

		for (Company comp : listCompany){
			System.out.println(comp.toString());
			System.out.println("----");

		}
	}
	public  void deleteCompany(Long companyToDelete) throws SQLException {
		companyDAO.deleteCompany(companyToDelete);
	}
}
