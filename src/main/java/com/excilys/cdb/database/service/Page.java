package com.excilys.cdb.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.ComputerDTO;


@Component
public class Page {
	Long computerPerPage=50L; 
	Long offsetPage=0L;
	int nextPageOK = 0;
	public  List<ComputerDTO> pageOfComputer;
	
	@Autowired
	private final ComputerDAO computerDao;
	@Autowired
	private final ServletServices servletServices;
	
	public Page(ComputerDAO computerDao, ServletServices servletServices) {
		this.computerDao = computerDao;
		this.servletServices = servletServices;
		this.pageOfComputer = servletServices.listPage(offsetPage, computerPerPage);
	}

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

	public  List<ComputerDTO> getPageOfComputer() {
		return pageOfComputer;
	}

	public  void setPageOfComputer(List<ComputerDTO> pageOfComputer) {
		this.pageOfComputer = pageOfComputer;
	}


}
