package com.excilys.cdb.database.mapperdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.ComputerDTO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.validator.FormatValidation;
import com.excilys.cdb.database.validator.DataValidation;


@Component
public class ComputerMapper{
	
	public class RowMapperComputer implements RowMapper{
		@Override
		public Computer mapRow(ResultSet rsComputer, int arg1) throws SQLException {
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
		
	}
	
	
	 Scanner sc = new Scanner(System.in);

	 @Autowired
	 private DataValidation dataControl;
	 @Autowired
	 private FormatValidation controlFormat;

	public  Computer rsToComputer(ResultSet rsComputer){
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

	public  LocalDate enterDiscontinuedDate() {
		LocalDate dateEnd= null;
		while(dateEnd==null) {
			String computerEndDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerEndDate.equals("")){
				dateEnd= dataControl.convertStringToTimestamp(computerEndDate);
			}else {break;}
		}
		return dateEnd;
	}

	public  LocalDate enterIntroductionDate() {
		LocalDate dateStart = null;
		while(dateStart==null) {
			String computerStartingDate = sc.nextLine();
			//Condition sur le remplissage du champ "date", l'utilisateur peut le laisser vide
			if(!computerStartingDate.equals("")){
				dateStart= dataControl.convertStringToTimestamp(computerStartingDate);
			}else{break;}
		}
		return dateStart;
	}

	public  String enterName() {
		String computerName = sc.nextLine();
		while(computerName.isEmpty()){
			computerName=sc.nextLine();
		}
		return computerName;
	}

	public  Long enterIdToFound() {
		String computerIDToSee = sc.nextLine();
		Long idComputerToSee = dataControl.stringToLongIDComputer(computerIDToSee, sc);
		return idComputerToSee;
	}

	public  Computer computerBuilder (String name, String companyId, String introduced, String discontinued) {
		Computer computer = new Computer(name, controlFormat.stringTolong(companyId), dataControl.convertStringToTimestamp(introduced),dataControl.convertStringToTimestamp(discontinued));		
		return computer;
	}


}
