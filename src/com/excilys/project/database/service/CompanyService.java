package com.excilys.project.database.service;

import java.util.List;

import com.excilys.project.database.datatype.Company;

public class CompanyService {

	/**
	 * 
	 * @param conn Connection établie au début
	 * @param sc Scanner input
	 * @param rsComputer Resultat de la query
	 */
	public static void viewAllCompany(List<Company> listCompany){
		//Demande du nombre d'ordinateurs par "page"
	
		for (Company comp : listCompany){
			System.out.println(comp.toString());
			System.out.println("----");

		}
	}

}
