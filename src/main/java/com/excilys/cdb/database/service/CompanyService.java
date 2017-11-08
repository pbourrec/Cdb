package com.excilys.cdb.database.service;

import java.util.List;

import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;

public class CompanyService {

	/**
	 * 
	 * @param conn Connection établie au début
	 * @param sc Scanner input
	 * @param rsComputer Resultat de la query
	 */
	public static void viewAllCompany(){
		//Demande du nombre d'ordinateurs par "page"
		List<Company> listCompany= CompanyDAO.databaseGetCompany();

		for (Company comp : listCompany){
			System.out.println(comp.toString());
			System.out.println("----");

		}
	}

}
