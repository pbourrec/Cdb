package com.excilys.cdb.database.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.controller.ControlFormat;
import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;

public class CompanyService {
	static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	/**
	 * 
	 * @param sc Scanner input
	 * @param rsComputer Resultat de la query
	 */
	public static void viewAllCompany(){
		//Demande du nombre d'ordinateurs par "page"
		List<Company> listCompany= CompanyDAO.getCompany();

		for (Company comp : listCompany){
			System.out.println(comp.toString());
			System.out.println("----");

		}
	}
	public static void deleteCompany(Long companyToDelete) throws SQLException {
			CompanyDAO.deleteCompany(companyToDelete);
	}
}
