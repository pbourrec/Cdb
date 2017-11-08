package com.excilys.cdb.database.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.ihm.UserInterface;
import com.excilys.cdb.database.mapper.CompanyMapper;
import com.excilys.cdb.database.mapper.ComputerMapper;

public class ComputerService {
	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

	/**
	 * 
	 * @param conn Connection intialisée au début
	 * @param  Scanner d'entrée utilisateur
	 * @param rsCompany ResultSet de la query à la table "companies"
	 * @param rsComputer ResultSet de la query à la table "computer"
	 * @return 
	 * @return boolean repeat doit on enter à nouveau un ordinateur
	 */
	public static  void add(){
		logger.debug("Entrée dans ComputerService.add");

		String computerName = ComputerMapper.enterName();
		CompanyService.viewAllCompany();
		Long idCompany = CompanyMapper.enterCompanyId();
		LocalDate dateStart = ComputerMapper.enterIntroductionDate();
		LocalDate dateEnd = ComputerMapper.enterDiscontinuedDate();

		Computer newComputer = new Computer(computerName,idCompany, dateStart, dateEnd);	
		if(UserInterface.confirmActionUpload(newComputer)){
			ComputerDAO.databaseUpload(newComputer);
		}
	}

	/**
	 * @param conn Connection intialisée au début
	 * @param  Scanner d'entrée utilisateur
	 * @param rsCompany ResultSet de la query à la table "companies"
	 * @param rsComputer ResultSet de la query à la table "computer"
	 * @return
	 */
	public static void edit (){
		//controle validité de l'ID
		Long idComputer = ComputerMapper.enterIdToFound();
		Computer computerToEdit = ComputerDAO.databaseQueryOne( idComputer);
		System.out.println(computerToEdit.toString());
		
		String computerName = ComputerMapper.enterName();
		CompanyService.viewAllCompany();
		Long idCompany = CompanyMapper.enterCompanyId();
		LocalDate dateStart = ComputerMapper.enterIntroductionDate();
		LocalDate dateEnd = ComputerMapper.enterDiscontinuedDate();
		// Creation d'un nouvel ordinateur et confirmation des données
		Computer newComputerUpdate = new Computer(computerName, idCompany, dateStart, dateEnd);
		if(UserInterface.confirmActionUpdate(newComputerUpdate)) {
			ComputerDAO.databaseUpdate(newComputerUpdate, idComputer);
		}		
	}

	
	public static void viewAll(){
		//Demande du nombre d'ordinateurs par "page"
		List<Computer> listComputer= ComputerDAO.databaseGetComputer();

		for (Computer comp : listComputer){
			System.out.println(comp.toString());
			System.out.println("----");
		}
	}

	/**
	 * 	
	 * @param  Scanner input
	 * @param conn Connection établie au débubt
	 * @param rsComputer Resultat de la query sur la table "computer"
	 * @return
	 */
	public static void viewOne (){
		Long idComputerToSee = ComputerMapper.enterIdToFound();
		Computer computerToShow = ComputerDAO.databaseQueryOne( idComputerToSee);
		System.out.println(computerToShow.toString());

	}

	/**
	 * 
	 * @param  scanner input clavier
	 * @param conn connection établie au début
	 * @param rsComputer Resultat de la Query sur la table "computer"
	 * @return
	 */
	public static void delete(){
		Long idComputerToDelete= ComputerMapper.enterIdToFound();
		Computer computerToDelete = ComputerDAO.databaseQueryOne( idComputerToDelete);

		if(UserInterface.confirmActionDelete(computerToDelete)) {
			ComputerDAO.databaseDelete(idComputerToDelete);
		}
	}

}
