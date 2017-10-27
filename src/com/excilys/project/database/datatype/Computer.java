package com.excilys.project.database.datatype;
//import java.util.Date;
import java.sql.*;


public class Computer {
	
	
	private Long id;
	private  String computerName;
	private long ManufacturerID;
	
	private Date dateIntroduced;
	private Date dateDiscontinued;
	
	public long getComputerManufacturer() {
		return ManufacturerID;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id=id;
	}
	
	public String getComputerName() {
		return computerName;
	}
	public Date getDateIntroduced() {
		return dateIntroduced;
	}
	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public void setDateIntroduced(Date dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}
	public void setDateDiscontinued(Date dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
		
	}
	public void setComputerManufacturer(long computerManufacturer) {
		this.ManufacturerID = computerManufacturer;
	}

	public Computer(String computerName) {
		super();
		this.computerName = computerName;
	}
	
	//constructeur si on a toutes les données
	public Computer(String computerName, long computerManufacturer, Date dateIntroduced,
			Date dateDiscontinued) {
		super();
		this.computerName = computerName;
		this.ManufacturerID = computerManufacturer;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
	}
	
	//Constructeur si l'on n'a que le nom et le fabricant
	/**
	 * @deprecated
	 * @param computerName
	 * @param computerManufacturer
	 */
	public Computer(String computerName, long computerManufacturer) {
		this.computerName = computerName;
		this.ManufacturerID = computerManufacturer;
	}

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "id N°---" + id + "\n Nom de l'ordinateur ---" + computerName
				+ "\n ID fabricant ---" + ManufacturerID + "\n Date mise en route ---"
				+ dateIntroduced + "\n Date mise en retraite ---" + dateDiscontinued;
	}
	
		
	

}
