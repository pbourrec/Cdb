package com.excilys.cdb.database.core;

import java.util.List;

import org.springframework.stereotype.Component;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.mapperdto.ComputerDTOMapper;
import com.excilys.cdb.database.service.ServletServices;


@Component
public class Page {
	Long computerPerPage=50L; 
	Long offsetPage=0L;
	public  List<ComputerDTO> pageOfComputer;
	int nextPageOK = 0;
	
	private final ComputerDTOMapper computerDtoMapper;
	private final ComputerDAO computerDao;
	private final ServletServices servletServices;
	
	public Page(ComputerDAO computerDao, ServletServices servletServices, ComputerDTOMapper computerDtoMapper) {
		this.computerDao = computerDao;
		this.servletServices = servletServices;
		this.computerDtoMapper = computerDtoMapper;
		this.pageOfComputer = computerDtoMapper.computerListToDTO(servletServices.listPage(this));
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
