package com.excilys.cdb.database.datatype;

import org.springframework.stereotype.Component;

public class ComputerDTO {
	private long id;
	private String computerName;
	private String companyName;
	private long companyId;

	private String dateIntroduced;
	private String dateDiscontinued;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getDateIntroduced() {
		return dateIntroduced;
	}

	public void setDateIntroduced(String dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	public String getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(String dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}



	public ComputerDTO(long computerId,String computerName, String companyName, long companyId, String dateIntroduced,
			String dateDiscontinued) {
		super();
		this.id = computerId;
		this.computerName = computerName;
		this.companyName = companyName;
		this.companyId = companyId;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
	}

	public ComputerDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "id N°---" + id + "\n Nom de l'ordinateur ---" + computerName
				+ "\n ID fabricant ---" + this.companyId + "\n String mise en route ---"
				+ dateIntroduced + "\n String mise en retraite ---" + dateDiscontinued;
	}




}

