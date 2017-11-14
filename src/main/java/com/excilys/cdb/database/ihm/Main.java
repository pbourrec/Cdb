package com.excilys.cdb.database.ihm;

import com.excilys.cdb.database.service.ComputerService;

public class Main {
	public static void main(String[] args) {
		System.out.println("Bonjour utilisateur");
		//String qui nous donnera l'autorisation de quitter le programme
		String endOfSession = "";
		while (!endOfSession.equals("quit")) {
//			String choiceNumber = UserInterface.cliChoice();
//			switch (choiceNumber) {
//
//			//Création d'un ordinateur
//			case "1":
//				ComputerService.add();					
//				break;
//
//				//Edition d'un ordinateur
//			case "2":
//				ComputerService.edit();					
//				break;
//
//				//Retour de la liste des ordinateurs
//			case "3":
//				ComputerService.viewAll();
//				break;
//
//				//Voir UN ordinateur
//			case "4":
//				ComputerService.viewOne();
//				break;
//
//				//Supprimer un ordinateur
//			case "5":
//				ComputerService.delete();
//				break;
//
//				//Finir la session
//			case "6":
//				endOfSession = UserInterface.exitProgram();
//				break;
//
//			default:
//				System.out.println("erreur de choix");
//				break;
//			}
		}
	}
}
