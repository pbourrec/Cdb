package org.webapp;

import java.util.List;

import org.model.ComputerDTO;
import org.persistence.ComputerRepository;
import org.service.ServletServices;
import org.springframework.stereotype.Component;


@Component
public class PageDto {
	Long computerPerPage=50L; 
	Long offsetPage=0L;
	public  List<ComputerDTO> pageOfComputer;
	int nextPageOK = 0;
	
	
	public PageDto ( ComputerRepository computerJpaDao, ServletServices servletServices, List<ComputerDTO> computerDto) {
		this.pageOfComputer = computerDto;
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
