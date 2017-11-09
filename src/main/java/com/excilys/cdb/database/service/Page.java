package com.excilys.cdb.database.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Computer;

public class Page {
	Long computerPerPage=50L; 
	Long offsetPage=0L;
	int nextPageOK = 0;
	public  List<Computer> pageOfComputer = listPage(offsetPage, computerPerPage);

	public  Long getComputerPerPage() {
		return computerPerPage;
	}

	public int getNextPageOK() {
		return nextPageOK;
	}

	public void setNextPageOK(int nextPageOK) {
		this.nextPageOK = nextPageOK;
	}

	public void setComputerPerPage(Long computerPerPage) {
		this.computerPerPage = computerPerPage;
	}

	public  Long getOffsetPage() {
		return offsetPage;
	}

	public  void setOffsetPage(Long offsetPage) {
		this.offsetPage = offsetPage;
	}

	public Page(Long limit, Long offset) {
		super();
		this.computerPerPage = limit;
		this.offsetPage = offset;
		this.pageOfComputer = ComputerDAO.getComputerPagination(offset, limit);
	}

	public Page() {
	}

	public  List<Computer> getPageOfComputer() {
		return pageOfComputer;
	}

	public  void setPageOfComputer(List<Computer> pageOfComputer) {
		this.pageOfComputer = pageOfComputer;
	}

	public static List<Computer>  listPage(Long offset, Long limit) {
		List<Computer> listComputer = ComputerDAO.getComputerPagination(offset, limit);
		return listComputer;

	}
}
