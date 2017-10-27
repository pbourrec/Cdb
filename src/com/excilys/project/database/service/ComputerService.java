package com.excilys.project.database.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.project.database.controller.DataControl;
import com.excilys.project.database.dao.ComputerDAO;
import com.excilys.project.database.datatype.Company;
import com.excilys.project.database.datatype.Computer;

public class ComputerService {
	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	/**
	 * 
	 * @param conn Connection intialisée au début
	 * @param sc Scanner d'entrée utilisateur
	 * @param rsCompany ResultSet de la query à la table "companies"
	 * @param rsComputer ResultSet de la query à la table "computer"
	 * @return boolean repeat doit on enter à nouveau un ordinateur
	 */
	public static boolean add(Scanner sc, List<Company> listCompany, List<Computer> listComputer){
		logger.debug("Entrée dans ComputerService.add");
		boolean repeat=false;
		System.out.println("*******************************************************************");
		System.out.println("Quel sera le nom de l'ordinateur");
		String computerName = sc.nextLine();
		while(computerName.isEmpty()){
			System.out.println("Le nom de l'ordinateur ne peut être vide, merci de remplir ce champ");
			computerName=sc.nextLine();
		}
		//Affichage de la liste des fabricants
		CompanyService.viewAllCompany(listCompany);
		System.out.println("Quel sera le constructeur de l'ordinateur (choisir un ID)" );
	
		String computerManufacturer = sc.nextLine();
		Long idCompany = DataControl.stringToLongIDCompany( computerManufacturer, sc);
	
	
		System.out.println("Date de mise en service de l'ordinateur (format dd/MM/yyyy)");
		Date dateStart = null;
		while(dateStart==null) {
			String computerStartingDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerStartingDate.equals("")){
				dateStart= DataControl.convertStringToTimestamp(computerStartingDate);
			}else{break;}
		}
		System.out.println("Date de mise en retraite de l'ordinateur (format dd/MM/yyyy)");
	
		Date dateEnd= null;
		while(dateEnd==null) {
			String computerEndDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerEndDate.equals("")){
				dateEnd= DataControl.convertStringToTimestamp(computerEndDate);
			}else {break;}
		}
	
		// *******************
		Computer newComputer = new Computer(computerName,idCompany, dateStart, dateEnd);
	
		System.out.println("Voulez vous bien créer l'ordinateur suivant : Y/N");
		System.out.println(newComputer.toString());
		String choice = sc.nextLine();
		//errorTypo empechera l'utilisateur de quitter l'ajout d'ordinateur s'il fait une faute ou une typo
		boolean errorTypo=false;
		while(!errorTypo){
			if (choice.equals("Y")) {
				boolean stateUpload = ComputerDAO.databaseUpload(newComputer);
				logger.debug("State of upload  " + stateUpload);
				System.out.println("Voulez vous en rajouter un autre? Y/N");
				choice=sc.nextLine();
				//l'utilisateur a créé un ordi, veut il en rajouter un autre ? 
				if(choice.equals("Y") || choice.equals("y")){
					repeat=true;
					errorTypo=true;
				} else{
					repeat = false;
					errorTypo= true;
					System.out.println("Retour au menu principal...");
				}
	
			} else if(choice.equals("N") || choice.equals("n")) {
				System.out.println("Abandon de l'ajout...");
				//l'utilisateur a fait une erreur dans la création de son ordinateur, il peut recommencer la procédure
				System.out.println("Voulez vous en ajouter un autre ? Y/N");
				choice=sc.nextLine();
				if(choice.equals("Y") || choice.equals("y")){
					errorTypo=true;
					repeat=true;
				} else{
					repeat = false;
					errorTypo= true;
					System.out.println("Retour au menu principal...");
				}
			} else{
				errorTypo=false;
			}
		}
		return repeat;
	}

	/**
	 * @param conn Connection intialisée au début
	 * @param sc Scanner d'entrée utilisateur
	 * @param rsCompany ResultSet de la query à la table "companies"
	 * @param rsComputer ResultSet de la query à la table "computer"
	 * @return
	 */
	public static boolean edit ( Scanner sc, List<Computer> listComputer, List<Company> listCompany){
		//Affichage de tous les ordinateurs
		System.out.println("Voulez vous voir la liste des ordinateurs ? Y/N");
		String seeList = sc.nextLine();
		if (seeList.equals("Y") || seeList.equals("y")){
			viewAll(listComputer);
		}
	
		//controle validité de l'ID
		String computerID = sc.nextLine();
		if(computerID.equals("exit") || computerID.equals("quit")){return false;}
	
		Long idComputer = DataControl.stringToLongIDCompany(computerID, sc);
	
		Computer computerToEdit = ComputerDAO.databaseQueryOne( idComputer);
		System.out.println(computerToEdit.toString());
		System.out.println("Nouveau nom ?");
		String computerNewName = sc.nextLine();
		while(computerNewName.isEmpty()){
			System.out.println("Le nom de l'ordinateur ne peut être vide, merci de remplir ce champ");
			computerNewName=sc.nextLine();
		}
		for (Company company: listCompany){
			System.out.println(company.toString());
		}
		System.out.println("Quel sera le nouveau constructeur de l'ordinateur (choisir un ID)" );
		String computerNewManufacturer = sc.nextLine();
		Long idNewCompany = DataControl.stringToLongIDCompany(computerNewManufacturer, sc);


		System.out.println("Nouvelle Date de mise en service de l'ordinateur (format dd/MM/yyyy)");
		Date newDateStart = null;
		while(newDateStart==null) {
			String computerNewStartingDate = sc.nextLine();

			if(!computerNewStartingDate.equals("")){

				newDateStart= DataControl.convertStringToTimestamp(computerNewStartingDate);
			}else{break;}
		}

		System.out.println("Nouvelle Date de mise en retraite de l'ordinateur (format dd/MM/yyyy)");
		Date newDateEnd= null;
		while(newDateEnd==null) {
			String computerNewEndDate = sc.nextLine();

			if (!computerNewEndDate.equals("")){

				newDateEnd= DataControl.convertStringToTimestamp(computerNewEndDate);
			}else {break;}
		}
		// Creation d'un nouvel ordinateur et confirmation des données
		Computer newComputerUpdate = new Computer(computerNewName, idNewCompany, newDateStart, newDateEnd);
		System.out.println("Voulez vous bien créer l'ordinateur suivant : Y/N");
		System.out.println("Nom : " + newComputerUpdate.getComputerName());
		System.out.println("Nom du constructeur : "+ newComputerUpdate.getComputerManufacturer());
		System.out.println("Mise en service: "+ newComputerUpdate.getDateIntroduced());
		System.out.println("Mise en retraite: "+ newComputerUpdate.getDateDiscontinued());
		String updateComputer = sc.nextLine();
		if(updateComputer.equals("Y")|| updateComputer.equals("y")){
			ComputerDAO.databaseUpdate(newComputerUpdate, idComputer);
		} else {
			System.out.println("Abandon de la modification");
		}
	
		return false;
	}

	public static void viewAll(List<Computer> listComputer ){
		//Demande du nombre d'ordinateurs par "page"
	
		for (Computer comp : listComputer){
			System.out.println(comp.toString());
			System.out.println("----");
		}
	}

	/**
	 * 	
	 * @param sc Scanner input
	 * @param conn Connection établie au débubt
	 * @param rsComputer Resultat de la query sur la table "computer"
	 * @return
	 */
	public static boolean viewOne (Scanner sc,List<Computer> listComputer){
		boolean repeat;
	
		System.out.println("Quel ordinateur voulez vous voir ? Entrez l'ID");
		String computerIDToSee = sc.nextLine();
		if(computerIDToSee.equals("exit") || computerIDToSee.equals("quit")){return repeat =false;}
		Long idComputerToSee = DataControl.stringToLongIDComputer(computerIDToSee, sc);
	
		//Recherche d'UN ordinateur
		Computer computerToShow = ComputerDAO.databaseQueryOne( idComputerToSee);
		System.out.println(computerToShow.toString());
		System.out.println("Voulez vous en voir un autre ? Y/N");
		String choice=sc.nextLine();
		if(choice.equals("Y")){
			repeat=true;
		} else{
			repeat = false;
		} 
		return repeat;
	}

	/**
	 * 
	 * @param sc scanner input clavier
	 * @param conn connection établie au début
	 * @param rsComputer Resultat de la Query sur la table "computer"
	 * @return
	 */
	public static boolean delete(Scanner sc,  List<Computer> listComputer){
		boolean repeat;
		//Affichage de tous les ordinateurs
		System.out.println("Voulez vous voir la liste des ordinateurs ? Y/N");
		String seeList = sc.nextLine();
		if (seeList.equals("Y") || seeList.equals("y")){
			viewAll(listComputer);
		}
	
		System.out.println("Quel ordinateur voulez vous supprimer ? Entrez l'ID");
	
		String computerIDToDelete= sc.nextLine();
		if(computerIDToDelete.equals("exit") || computerIDToDelete.equals("quit")){return repeat =false;}
		Long idComputerToDelete = DataControl.stringToLongIDComputer(computerIDToDelete, sc);
		Computer computerToDelete = ComputerDAO.databaseQueryOne( idComputerToDelete);
		System.out.println(computerToDelete.toString());
		System.out.println("Voulez vous supprimer cet ordinateur ?");
		String areYouSure = sc.nextLine();
		if(areYouSure.equals("Y")|| areYouSure.equals("y")){
			ComputerDAO.databaseDelete(idComputerToDelete);
		} else{
			System.out.println("Abandon de la suppression");
		}
		System.out.println("Voulez vous en supprimer  un autre ? ? Y/N");
		String choice=sc.nextLine();
		if(choice.equals("Y")){
			repeat=true;
		} else{
			repeat = false;
		} 
		return repeat;
	}

}
