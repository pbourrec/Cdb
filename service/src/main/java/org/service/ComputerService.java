package org.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import org.model.Computer;
import org.persistence.ComputerRepository;
import org.validator.*;

@Service
public class ComputerService {
	private  Logger logger = LoggerFactory.getLogger(ComputerService.class);
	
	@Autowired
	private ComputerRepository computerJpaDao;
	@Autowired
	private CompanyService companyService;
	 @Autowired
	 private DataValidation dataControl;
	 @Autowired
	 private FormatValidation controlFormat;


	public  void addComputer(String computerName, String introduced, String discontinued, String companyId) {
		Computer computerToAdd = new Computer();
		computerToAdd = computerBuilder(computerName, companyId, introduced, discontinued);
		computerJpaDao.save(computerToAdd);
	}

	public  String deleteComputer(String idToDelete) {
		String[] listIdToDelete = idToDelete.split(",");
		String computersDeleted ="Les ordinateurs suivants on étés supprimés : \n";
		for(int i=0; i< listIdToDelete.length; i++) {
			computerJpaDao.deleteById(Long.valueOf(listIdToDelete[i]));
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
	
		computersToFind = computerJpaDao.getComputerByCompanyJpa(companyToFind);
	
		return computersToFind;
	}

	public  List<Computer> findComputersByCompanyId(Long companyIdToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = computerJpaDao.getComputerByCompanyIdJpa(companyIdToFind);
		return computersToFind;
	}

	public  List<Computer> findComputersByName(String nameToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = computerJpaDao.getComputerByNameJpa(nameToFind);
		return computersToFind;
	}

	public int getSizeComputer() {
		int sizeTable= (int) computerJpaDao.count();
		return sizeTable;
	}

	public Computer queryOneComputer(String computerId) {
		Computer computerToEdit = computerJpaDao.getComputerByIdJpa(Long.valueOf(computerId));
		return computerToEdit;
	}

	public void updateComputer(Long computerId, Computer computerToAdd) {
		computerToAdd.setId(computerId);
		computerJpaDao.save(computerToAdd);
	}
	

	public  Computer computerBuilder (String name, String companyId, String introduced, String discontinued) {
		Computer computer = new Computer(name, controlFormat.stringTolong(companyId), dataControl.convertStringToTimestamp(introduced),dataControl.convertStringToTimestamp(discontinued));		
		return computer;
	}

}
