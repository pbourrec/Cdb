package org.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


//import java.util.LocalDate;


@Entity(name="computer")
public class Computer {

	@Id
	@GeneratedValue(strategy=GenerationType. IDENTITY)
	private Long id;
	@Column(name="name")
	private String computerName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company = new Company();

	@Column(name="introduced")
	private LocalDate dateIntroduced;
	@Column	(name="discontinued")
	private LocalDate dateDiscontinued;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Computer(String computerName) {
		super();
		this.computerName = computerName;
	}

	//constructeur si on a toutes les données
	public Computer(String computerName, Long computerManufacturer, LocalDate dateStart,
			LocalDate dateEnd) {
		super();

		this.computerName = computerName;
		this.company.setId(computerManufacturer);
		this.dateIntroduced = dateStart;
		this.dateDiscontinued = dateEnd;
	}
	//constructeur si on a toutes les données
	public Computer(Long id,String computerName, Long computerManufacturer, LocalDate dateStart,
			LocalDate dateEnd) {
		super();
		this.id =id;
		this.computerName = computerName;
		this.company.setId(computerManufacturer);
		this.dateIntroduced = dateStart;
		this.dateDiscontinued = dateEnd;
	}

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "id N°---" + id + "\n Nom de l'ordinateur ---" + computerName
				+ "\n ID fabricant ---" + this.getCompany().getId() + "\n LocalDate mise en route ---"
				+ dateIntroduced + "\n LocalDate mise en retraite ---" + dateDiscontinued;
	}




}
