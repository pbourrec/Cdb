package com.excilys.cdb.database.datatype;

import java.sql.Date;
import java.time.LocalDate;

//import java.util.LocalDate;



public class Computer {
	
	
	private Long id;
	private String computerName;
	
	
	private Company company;

	private LocalDate dateIntroduced;
	private LocalDate dateDiscontinued;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//	public Long getManufacturerID() {
//		return manufacturerID;
//	}
//	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id=id;
	}
	
	public String getComputerName() {
		return computerName;
	}
	public LocalDate getDateIntroduced() {
		return dateIntroduced;
	}
	public LocalDate getDateDiscontinued() {
		return dateDiscontinued;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public void setDateIntroduced(LocalDate dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}
	public void setDateDiscontinued(LocalDate dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
		
	}
//	public void setComputerManufacturer(Long computerManufacturer) {
//		this.manufacturerID = computerManufacturer;
//	}

	public Computer(String computerName) {
		super();
		this.computerName = computerName;
	}
	
	//constructeur si on a toutes les données
	public Computer(String computerName, Long computerManufacturer, LocalDate dateStart,
			LocalDate dateEnd) {
		super();
		this.computerName = computerName;
		this.company.id = computerManufacturer;
		this.dateIntroduced = dateStart;
		this.dateDiscontinued = dateEnd;
	}
	
	//Constructeur si l'on n'a que le nom et le fabricant
	/**
	 * @deprecated
	 * @param computerName
	 * @param computerManufacturer
	 */
	public Computer(String computerName, Long computerManufacturer) {
		this.computerName = computerName;
		this.company.id = computerManufacturer;
	}

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "id N°---" + id + "\n Nom de l'ordinateur ---" + computerName
				+ "\n ID fabricant ---" + this.company.id + "\n LocalDate mise en route ---"
				+ dateIntroduced + "\n LocalDate mise en retraite ---" + dateDiscontinued;
	}
	
		
	

}
