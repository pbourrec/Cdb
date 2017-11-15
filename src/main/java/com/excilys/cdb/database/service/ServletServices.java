package com.excilys.cdb.database.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.CompanyDTO;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.datatype.ComputerDTO;
import com.excilys.cdb.database.mapper.CompanyMapper;
import com.excilys.cdb.database.mapper.ComputerMapper;

@Component 
public class ServletServices {
	@Autowired
	private ComputerDAO computerDao;
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private CompanyService companyService;


	public  int changePageFormat (String computerPerPageReciever, String operation,String pageChange, String restart, Page page) {

		int sizeTable= computerDao.getSizeComputer();
		if(computerPerPageReciever!=null) {
			page.setComputerPerPage( Long.valueOf(computerPerPageReciever));
			page.setOffsetPage(0L);
		} else if (operation!=null && page.getOffsetPage()+Long.valueOf(operation)>=0 && ((page.getOffsetPage()+Long.valueOf(operation))*page.getComputerPerPage())<sizeTable){
			page.setOffsetPage((Long.valueOf(operation)+page.getOffsetPage()));
		} else if (pageChange!=null && Long.valueOf(pageChange)*page.getComputerPerPage()<sizeTable){
			page.setOffsetPage(Long.valueOf(pageChange));
		}else if(restart!=null) {
			page.setComputerPerPage(50L);
			page.setOffsetPage(0L);
		}
		if(((page.getOffsetPage()+1)*page.getComputerPerPage())<sizeTable){
			page.setNextPageOK(1);
			if(((page.getOffsetPage()+2)*page.getComputerPerPage())<sizeTable) {
				page.setNextPageOK(2);

			}
		}else {
			page.setNextPageOK(-1);
		}
		return sizeTable;
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


	public  List<CompanyDTO> getListCompany(){
		List<Company> listCompany = companyDao.getCompany();
		List<CompanyDTO> listCompanyDTO=new ArrayList<>();
		for(Company company : listCompany) {
			listCompanyDTO.add(companyMapper.companyToDTO(company));
		}
		return listCompanyDTO;
	}


	public  List<ComputerDTO> findComputersByName(String nameToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		List<ComputerDTO> computersDTOToFind = new ArrayList<>();

		computersToFind = computerDao.getComputerByName(nameToFind);
		for(Computer comp : computersToFind) {
			computersDTOToFind.add(computerMapper.computerToDTO(comp));
		}
		return computersDTOToFind;
	}


	public  List<ComputerDTO> findComputersByCompany(String companyToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		List<ComputerDTO> computersDTOToFind = new ArrayList<>();

		computersToFind = computerDao.getComputerByCompany(companyToFind);
		for(Computer comp : computersToFind) {
			computersDTOToFind.add(computerMapper.computerToDTO(comp));
		}
		return computersDTOToFind;
	}


	public  List<ComputerDTO> findComputersByCompanyId(Long companyIdToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		List<ComputerDTO> computersDTOToFind = new ArrayList<>();
		computersToFind = computerDao.getComputerByCompanyId(companyIdToFind);
		for(Computer comp : computersToFind) {
			computersDTOToFind.add(computerMapper.computerToDTO(comp));
		}
		return computersDTOToFind;
	}

	public  void addComputer(String computerName, String introduced, String discontinued, String companyId) {
		Computer computerToAdd = new Computer();
		computerToAdd = computerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		System.out.println(computerToAdd.toString());
		computerDao.upload(computerToAdd);
	}

	public  void deleteComputerAndCompany(Long companyId) throws SQLException {
		companyService.deleteCompany(companyId);
	}

	public ComputerDTO queryOne(String computerId) {
		Computer computerToEdit = computerDao.queryOne(computerId !=null ? Long.valueOf(computerId) : 0L);
		return computerMapper.computerToDTO(computerToEdit);
	}


	public void updateComputer(String computerId, Computer computerToAdd) {
		computerDao.update(computerToAdd, Long.valueOf(computerId));
	}

	public int getSizeComputer() {
		 int sizeTable= computerDao.getSizeComputer();
		 return sizeTable;
	}
	public  List<ComputerDTO>  listPage(Long offset, Long limit) {
		List<ComputerDTO> listComputersDTO = new ArrayList<>();
		List<Computer> listComputer = computerDao.getComputerPagination(offset, limit);
		for(Computer comp : listComputer) {
			listComputersDTO.add(computerMapper.computerToDTO(comp));
		}
		return listComputersDTO;
	}

}
