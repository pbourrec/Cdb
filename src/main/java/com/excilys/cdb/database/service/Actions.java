package com.excilys.cdb.database.service;

import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;

public class Actions {
	/**
	 * 
	 * @param sc Scanner input clavier, on le ferme
	 * @param conn Fermeture de la connection
	 * @param rsComputer fermeture de la query sur la table "computer"
	 * @param rsCompany fermeture de la query sur la table "Company"
	 * @return String endOfSession pour quitter la boucle while du main
	 */
	public static String exitProgram (Scanner sc,  List<Computer> listComputer, List<Company> listCompany){
		System.out.println("Quitter la machine ? Y/N");
		String choice = sc.nextLine();
		String endOfSession = "";
		if (choice.equals("Y")) {
			System.out.println("Au revoir utilisateur, a bientot");
			endOfSession = "quit";

		} else if (choice.equals("N")) {
			endOfSession ="Stay";
		}
		//fermeture des variables
		sc.close();
		return endOfSession;
	}
}



