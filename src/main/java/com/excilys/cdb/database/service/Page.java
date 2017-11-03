package com.excilys.cdb.database.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Computer;

public class Page {
	
	public static Long limit;
	public static Long offset;
	public static List<Computer> pageOfComputer = new ArrayList();
	
	public Page(Long limit, Long offset) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.pageOfComputer = ComputerDAO.databaseGetComputer(offset, limit);
	}

	public static List<Computer> getPageOfComputer() {
		return pageOfComputer;
	}

	public static void setPageOfComputer(List<Computer> pageOfComputer) {
		Page.pageOfComputer = pageOfComputer;
	}
	
	public static List<Computer>  listPage(Long offset, Long limit) {
		List<Computer> listComputer = ComputerDAO.databaseGetComputer(offset, limit);
		return listComputer;

	}
}
