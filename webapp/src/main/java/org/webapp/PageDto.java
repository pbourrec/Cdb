package org.webapp;

import java.util.List;

import org.model.ComputerDTO;
import org.springframework.stereotype.Component;


@Component
public class PageDto {
	private Long computerPerPage=50L; 
	private Long offsetPage=0L;
	private List<ComputerDTO> pageOfComputer;
	int nextPageOK = 0;

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

	@Override
	public String toString() {
		return "PageDto [computerPerPage=" + computerPerPage + ", offsetPage=" + offsetPage + "nextPageOK=" + nextPageOK + "]";
	}




}
