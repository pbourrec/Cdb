package com.excilys.cdb.database.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.ihm.UserInterface;
import com.excilys.cdb.database.mapper.CompanyMapper;
import com.excilys.cdb.database.mapper.ComputerMapper;
@Service
public class ComputerService {
	private  Logger logger = LoggerFactory.getLogger(ComputerService.class);

	
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserInterface userInterface;

	/**
	 * 
	 */
	 
	  	public   void add(){
		logger.debug("Entrée dans ComputerService.add");

		String computerName = computerMapper.enterName();
		companyService.viewAllCompany();
		Long idCompany = companyMapper.enterCompanyId();
		LocalDate dateStart = computerMapper.enterIntroductionDate();
		LocalDate dateEnd = computerMapper.enterDiscontinuedDate();

		Computer newComputer = new Computer(computerName,idCompany, dateStart, dateEnd);	
		if(userInterface.confirmActionUpload(newComputer)){
			computerDao.upload(newComputer);
		}
	}

	/**
	 * @param  Scanner d'entrée utilisateur
	 * @param rsCompany ResultSet de la query à la table "companies"
	 * @param rsComputer ResultSet de la query à la table "computer"
	 * @return
	 */
	public  void edit (){
		//controle validité de l'ID
		Long idComputer = computerMapper.enterIdToFound();
		Computer computerToEdit = computerDao.queryOne( idComputer);
		System.out.println(computerToEdit.toString());
		
		String computerName = computerMapper.enterName();
		companyService.viewAllCompany();
		Long idCompany = companyMapper.enterCompanyId();
		LocalDate dateStart = computerMapper.enterIntroductionDate();
		LocalDate dateEnd = computerMapper.enterDiscontinuedDate();
		// Creation d'un nouvel ordinateur et confirmation des données
		Computer newComputerUpdate = new Computer(computerName, idCompany, dateStart, dateEnd);
		if(userInterface.confirmActionUpdate(newComputerUpdate)) {
			computerDao.update(newComputerUpdate, idComputer);
		}		
	}

	
	public  void viewAll(){
		//Demande du nombre d'ordinateurs par "page"
		List<Computer> listComputer= computerDao.getAllComputer();

		for (Computer comp : listComputer){
			System.out.println(comp.toString());
			System.out.println("----");
		}
	}

	/**	
	 * 	
	 * @param  Scanner input
	 * @param rsComputer Resultat de la query sur la table "computer"
	 * @return
	 */
	public  void viewOne (){
		Long idComputerToSee = computerMapper.enterIdToFound();
		Computer computerToShow = computerDao.queryOne( idComputerToSee);
		System.out.println(computerToShow.toString());

	}

	/**
	 * 
	 * @param  scanner input clavier
	 * @param rsComputer Resultat de la Query sur la table "computer"
	 * @return
	 */
	public  void delete(){
		Long idComputerToDelete= computerMapper.enterIdToFound();
		Computer computerToDelete = computerDao.queryOne( idComputerToDelete);

		if(userInterface.confirmActionDelete(computerToDelete)) {
			computerDao.delete(idComputerToDelete);
		}
	}

}
