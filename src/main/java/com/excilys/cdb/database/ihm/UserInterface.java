package com.excilys.cdb.database.ihm;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.service.ComputerService;

public class UserInterface {
	static Scanner sc = new Scanner(System.in);
	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	public static String cliChoice() {
		System.out.println("*******************************************************************");
		System.out.println("Que voulez vous faire : ");
		System.out.println("1. Ajouter un ordinateur");
		System.out.println("2. Modifier un ordinateur");
		System.out.println("3. Voir la liste des ordinateurs");
		System.out.println("4. Voir un ordinateur");
		System.out.println("5. Supprimer un ordinateur");
		System.out.println("6. Quitter le menu");

		String choiceNumber = sc.nextLine();
		return choiceNumber;

	}

	public static boolean confirmActionUpload(Computer computerToShow) {
		boolean uploadOK = false;
		System.out.println("Voulez vous bien créer l'ordinateur suivant : Y/N");
		System.out.println(computerToShow.toString());
		String choice = sc.nextLine();
		//errorTypo empechera l'utilisateur de quitter l'ajout d'ordinateur s'il fait une faute ou une typo
		boolean errorTypo=false;
		while(!errorTypo){
			if (choice.equals("Y")) {
				uploadOK= true;
			} else if(choice.equals("N") || choice.equals("n")) {
				System.out.println("Abandon de l'ajout...");
				uploadOK= false;
			} else{
				errorTypo=false;
			}
		}
		return uploadOK;
	}


	public static boolean confirmActionUpdate(Computer computerToShow) {
		System.out.println("Voulez vous bien créer l'ordinateur suivant : Y/N");
		System.out.println(computerToShow.toString());
		String updateComputer = sc.nextLine();
		if(updateComputer.equals("Y")|| updateComputer.equals("y")){
			return true;
		} else {
			System.out.println("Abandon de la modification");
			return false;
		}

	}
	public static boolean confirmActionDelete(Computer computerToShow) {

		System.out.println(computerToShow.toString());
		System.out.println("Voulez vous supprimer cet ordinateur ?");

		String areYouSure = sc.nextLine();
		if(areYouSure.equals("Y")|| areYouSure.equals("y")){
			return true;
		} else{
			System.out.println("Abandon de la suppression");
			return false;
		}

	}

	/**
	 * 
	 * @param sc Scanner input clavier, on le ferme
	 * @param rsComputer fermeture de la query sur la table "computer"
	 * @param rsCompany fermeture de la query sur la table "Company"
	 * @return String endOfSession pour quitter la boucle while du main
	 */
	public static String exitProgram (){
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
