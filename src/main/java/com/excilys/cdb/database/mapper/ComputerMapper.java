package com.excilys.cdb.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.controller.ControlFormat;
import com.excilys.cdb.database.controller.DataControl;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;

public class ComputerMapper {
	static Scanner sc = new Scanner(System.in);


	public static Computer rsToComputer(ResultSet rsComputer){
		final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
		Computer computerConvert = new Computer();
		try {

			computerConvert.setId(rsComputer.getLong(1));
			computerConvert.setComputerName(rsComputer.getString(2));
			java.sql.Date dateIntroduced = rsComputer.getDate(3);
			java.sql.Date datediscontinued= rsComputer.getDate(4);
			computerConvert.setDateIntroduced(dateIntroduced!= null ? dateIntroduced.toLocalDate() : null);
			computerConvert.setDateDiscontinued(datediscontinued!= null ? datediscontinued.toLocalDate() : null);
			computerConvert.setCompany(new Company(rsComputer.getLong(5),(rsComputer.getString(7)) != null ?rsComputer.getString(7) : null ));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return null;
		}

		return computerConvert;		
	}

	public static LocalDate enterDiscontinuedDate() {
		System.out.println("LocalDate de mise en retraite de l'ordinateur (format dd/MM/yyyy)");	
		LocalDate dateEnd= null;
		while(dateEnd==null) {
			String computerEndDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerEndDate.equals("")){
				dateEnd= DataControl.convertStringToTimestamp(computerEndDate);
			}else {break;}
		}
		return dateEnd;
	}

	public static LocalDate enterIntroductionDate() {
		System.out.println("LocalDate de mise en service de l'ordinateur (format dd/MM/yyyy)");
		LocalDate dateStart = null;
		while(dateStart==null) {
			String computerStartingDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerStartingDate.equals("")){
				dateStart= DataControl.convertStringToTimestamp(computerStartingDate);
			}else{break;}
		}
		return dateStart;
	}

	public static String enterName() {
		System.out.println("Quel sera le nom de l'ordinateur");
		String computerName = sc.nextLine();
		while(computerName.isEmpty()){
			System.out.println("Le nom de l'ordinateur ne peut être vide, merci de remplir ce champ");
			computerName=sc.nextLine();
		}
		return computerName;
	}

	public static Long enterIdToFound() {
		System.out.println("Quel ordinateur voulez vous voir ? Entrez l'ID");
		String computerIDToSee = sc.nextLine();
		Long idComputerToSee = DataControl.stringToLongIDComputer(computerIDToSee, sc);
		return idComputerToSee;
	}

	public static Computer computerBuilder (String name, String companyId, String introduced, String discontinued) {
		System.out.println("nom " + name +", companyId " + companyId +", introduced + " + introduced + ", discontinued" + discontinued);
		Computer computer = new Computer(name, ControlFormat.stringTolong(companyId), DataControl.convertStringToTimestamp(introduced),DataControl.convertStringToTimestamp(discontinued));		
		return computer;
	}


}
