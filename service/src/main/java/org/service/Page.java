package org.service;

import java.util.List;

import org.model.Computer;
import org.model.ComputerDTO;
import org.persistence.ComputerRepository;
import org.service.ServletServices;
import org.springframework.stereotype.Component;


@Component
public class Page {
	Long computerPerPage=50L; 
	Long offsetPage=0L;
	public  List<Computer> pageOfComputer;
	int nextPageOK = 0;
	
	private final Computer computer;
	private final ComputerRepository computerJpaDao;
	private final ServletServices servletServices;
	
	public Page( ComputerRepository computerJpaDao, ServletServices servletServices, Computer computer) {
		this.computerJpaDao = computerJpaDao;
		this.servletServices = servletServices;
		this.computer= computer;
		this.pageOfComputer = listPage(servletServices);
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

	public  List<Computer> getPageOfComputer() {
		return pageOfComputer;
	}

	public  void setPageOfComputer(List<Computer> pageOfComputer) {
		this.pageOfComputer = pageOfComputer;
	}

	public  List<Computer>  listPage(ServletServices servletServices) {
		Long offset = getOffsetPage()*getComputerPerPage();
		Long limit =getComputerPerPage();
		List<Computer> listComputer = servletServices.computerJpaDao.getComputerPaginationJpa(offset, limit);
		return listComputer;
	}


}
