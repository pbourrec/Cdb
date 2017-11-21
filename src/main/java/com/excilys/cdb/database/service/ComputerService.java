package com.excilys.cdb.database.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.ihm.UserInterface;
import com.excilys.cdb.database.mapperdao.CompanyMapper;
import com.excilys.cdb.database.mapperdao.ComputerMapper;
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

	public  void addComputer(String computerName, String introduced, String discontinued, String companyId) {
		Computer computerToAdd = new Computer();
		computerToAdd = computerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		computerDao.upload(computerToAdd);
	}

	public  String deleteComputer(String idToDelete) {
		String[] listIdToDelete = idToDelete.split(",");
		String computersDeleted ="Les ordinateurs suivants on étés supprimés : \n";
		for(int i=0; i< listIdToDelete.length; i++) {
			computerDao.delete(Long.valueOf(listIdToDelete[i]));
			computersDeleted+=" " +listIdToDelete[i] + ",";
		}
		computersDeleted = computersDeleted.substring(0, computersDeleted.length() - 1);
		return computersDeleted;
	}

	public  void deleteComputerAndCompany(Long companyId) throws SQLException {
		companyService.deleteCompany(companyId);
	}

	public  List<Computer> findComputersByCompany(String companyToFind) {
		List<Computer> computersToFind = new ArrayList<>();
	
		computersToFind = computerDao.getComputerByCompany(companyToFind);
	
		return computersToFind;
	}

	public  List<Computer> findComputersByCompanyId(Long companyIdToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = computerDao.getComputerByCompanyId(companyIdToFind);
		return computersToFind;
	}

	public  List<Computer> findComputersByName(String nameToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = computerDao.getComputerByName(nameToFind);
		return computersToFind;
	}

	public int getSizeComputer() {
		int sizeTable= computerDao.getSizeComputer();
		return sizeTable;
	}

	public Computer queryOneComputer(String computerId) {
		Computer computerToEdit = computerDao.queryOne(Long.valueOf(computerId));
		return computerToEdit;
	}

	public void updateComputer(Long computerId, Computer computerToAdd) {
		computerDao.update(computerToAdd, computerId);
	}

}
