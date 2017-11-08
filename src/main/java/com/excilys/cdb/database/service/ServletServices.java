package com.excilys.cdb.database.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;

public class ServletServices {

	@SuppressWarnings("static-access")
	public static int changePageFormat (String computerPerPageReciever, String operation,String pageChange, String restart, Page page) {

		int sizeTable= ComputerDAO.databaseGetSizeComputer();
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


	public static String servletDeleteComputer(String idToDelete) {
		String[] listIdToDelete = idToDelete.split(",");
		String computersDeleted ="Les ordinateurs suivants on étés supprimés : \n";
		for(int i=0; i< listIdToDelete.length; i++) {
			ComputerDAO.databaseDelete(Long.valueOf(listIdToDelete[i]));
			computersDeleted+=" " +listIdToDelete[i] + ",";
		}
		computersDeleted = computersDeleted.substring(0, computersDeleted.length() - 1);
		return computersDeleted;
	}

	
	public static List<Company> getListCompany(){
		return CompanyDAO.databaseGetCompany();

		
	}


	public static List<Computer> findComputersByName(String nameToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = ComputerDAO.databaseGetComputerByName(nameToFind);
		return computersToFind;
	}


	public static List<Computer> findComputersByCompany(String companyToFind) {
		List<Computer> computersToFind = new ArrayList<>();
		computersToFind = ComputerDAO.databaseGetComputerByCompany(companyToFind);
		return computersToFind;
	}
	
	public static void addComputer(String computerName, String introduced, String discontinued, String companyId) {
		Computer computerToAdd = new Computer();
		computerToAdd = ComputerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		System.out.println(computerToAdd.toString());
		ComputerDAO.databaseUpload(computerToAdd);
	}

}
