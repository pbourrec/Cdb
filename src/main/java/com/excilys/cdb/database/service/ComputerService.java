package com.excilys.cdb.database.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;

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
		String computerName = ComputerMapper.enterName(sc);
		//Affichage de la liste des fabricants
		CompanyService.viewAllCompany(listCompany);
	
		Long idCompany = ComputerMapper.enterCompanyId(sc);
	
		LocalDate dateStart = ComputerMapper.enterIntroductionDate(sc);
		
		LocalDate dateEnd = ComputerMapper.enterDiscontinuedDate(sc);
	
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
	public static void edit ( Scanner sc, List<Computer> listComputer, List<Company> listCompany){
		//Affichage de tous les ordinateurs
		System.out.println("Voulez vous voir la liste des ordinateurs ? Y/N");
		String seeList = sc.nextLine();
		if (seeList.equals("Y") || seeList.equals("y")){
			viewAll(listComputer);
		}
	
		//controle validité de l'ID
		Long idComputer = ComputerMapper.enterIdToFound(sc);
		Computer computerToEdit = ComputerDAO.databaseQueryOne( idComputer);
		System.out.println(computerToEdit.toString());
		String computerName = ComputerMapper.enterName(sc);
		//Affichage de la liste des fabricants
		CompanyService.viewAllCompany(listCompany);
	
		Long idCompany = ComputerMapper.enterCompanyId(sc);
	
		LocalDate dateStart = ComputerMapper.enterIntroductionDate(sc);
		
		LocalDate dateEnd = ComputerMapper.enterDiscontinuedDate(sc);
	
		// Creation d'un nouvel ordinateur et confirmation des données
		Computer newComputerUpdate = new Computer(computerName, idCompany, dateStart, dateEnd);
		System.out.println("Voulez vous bien créer l'ordinateur suivant : Y/N");
		System.out.println(newComputerUpdate.toString());
		String updateComputer = sc.nextLine();
		if(updateComputer.equals("Y")|| updateComputer.equals("y")){
			ComputerDAO.databaseUpdate(newComputerUpdate, idComputer);
		} else {
			System.out.println("Abandon de la modification");
		}
	
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
	
		Long idComputerToSee = ComputerMapper.enterIdToFound(sc);
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
		Long idComputerToDelete= ComputerMapper.enterIdToFound(sc);
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
