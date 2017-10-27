package com.excilys.project.database.ihm;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.excilys.project.database.dao.ComputerDAO;
import com.excilys.project.database.dao.CompanyDAO;
import com.excilys.project.database.dao.DatabaseConn;
import com.excilys.project.database.datatype.Company;
import com.excilys.project.database.datatype.Computer;
import com.excilys.project.database.service.Actions;
import com.excilys.project.database.service.ComputerService;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Bonjour utilisateur");

		//String qui nous donnera l'autorisation de quitter le programme
		String endOfSession = "";
		while (!endOfSession.equals("quit")) {
			boolean repeat=true;
			System.out.println("*******************************************************************");
			//Connection a la database et query sur les tables "computer" et "coexcmpany"
			List<Company> listCompany= CompanyDAO.databaseGetCompany();
			List<Computer> listComputer= ComputerDAO.databaseGetComputer();

			System.out.println("Que voulez vous faire : ");
			System.out.println("1. Ajouter un ordinateur");
			System.out.println("2. Modifier un ordinateur");
			System.out.println("3. Voir la liste des ordinateurs");
			System.out.println("4. Voir un ordinateur");
			System.out.println("5. Supprimer un ordinateur");
			System.out.println("6. Quitter le menu");

			//String pour nous donner la direction du switch
			String choiceNumber = sc.nextLine();

			switch (choiceNumber) {
			//Création d'un ordinateur
			case "1":
				while(repeat){
					repeat = ComputerService.add(sc, listCompany, listComputer);					
				}
				break;
				//Edition d'un ordinateur
			case "2":
				while(repeat){
					repeat = ComputerService.edit(sc, listComputer, listCompany);					
				}
				break;
				//Retour de la liste des ordinateurs
			case "3":
				ComputerService.viewAll(listComputer);
				break;
				//Voir UN ordinateur
			case "4":
				while(repeat){
					repeat = ComputerService.viewOne(sc, listComputer);
				}

				break;
				//Supprimer un ordinateur
			case "5":
				while(repeat){
					repeat= ComputerService.delete(sc, listComputer);
				}

				break;
				//Finir la session
			case "6":
				endOfSession = Actions.exitProgram(sc, listComputer, listCompany);
				break;

			default:
				System.out.println("erreur de choix");
				break;
			}
		}
	}
}
