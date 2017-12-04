package org.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataValidation {
	Logger logger = LoggerFactory.getLogger(DataValidation.class);
	@Autowired
	private  ConditionValidation conditionControl;

	/**
	 * 
	 * @param computerManufacturer ID du fabricant
	 * @param sc Scanner input clavier
	 * @return Long ValueOf ID du fabricant
	 */
	public  Long stringToLongIDCompany(String computerManufacturer, Scanner sc ){
		logger.debug("Conversion String vers id");
		//dans le cas ou l'entrée est nulle, on ne prendra pas de constructeur
		if(!computerManufacturer.equals("")){
			//tant que l'ID n'existe pas ou n'est pas valide
			while (!conditionControl.isIdCompanyValid(computerManufacturer)) {
				logger.info("l'ID rentré n'existe pas ou n'est pas sous le bon format, merci de reessayer");
				computerManufacturer = sc.nextLine();
			}
			return Long.valueOf(computerManufacturer);
		}
		else {return 0l;}	
	}
	/**
	 * 
	 * @param computerID ID de l'ordinateur
	 * @param sc Scanner input clavier
	 * @return Long valueOf ID de l'ordinateur
	 */
	public  Long stringToLongIDComputer(String computerID, Scanner sc ){

		//tant que l'ID n'existe pas ou n'est pas valide
		while (!conditionControl.isIdComputerValid(computerID)) {
			logger.info("l'ID rentré n'existe pas ou n'est pas sous le bon format, merci de reessayer");
			computerID = sc.nextLine();
		}
		return Long.valueOf(computerID);

	}

	/**
	 * 
	 * @param stringToInt String a convertir en Integer
	 * @param sc Scanner input clavier
	 * @return Integer intReturned nombre d'ordinateurs affiché par "page"
	 */
	public  Integer stringToInt(String stringToInt, Scanner sc ){
		boolean isOK= false;
		//On affiche 50 pages par défaut
		Integer intReturned=50 ;
		while (!isOK) {
			try{
				intReturned= Integer.valueOf(stringToInt);
				isOK = true;

			} catch (NumberFormatException nfe) {
				logger.info("La valeur rentrée n'est pas valable, merci de recommencer");
				stringToInt = sc.nextLine();
				isOK=false;
			}
		}
		return intReturned;
	}

	/**
	 * 
	 * @param str_date String à convertir en LocalDate
	 * @return java.sql.LocalDate LocalDate issue de la string
	 */
	public  LocalDate convertStringToTimestamp(String str_date) {
		logger.info("Conversion string to timestap with string" + str_date);
		//creation du format de la LocalDate
		LocalDate localDate = null;
		if(str_date!=null) {
			if(!str_date.equals("")) {
				try{DateTimeFormatter formatter;
				formatter = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();
				// you can change format of LocalDate
				localDate =LocalDate.parse(str_date, formatter);
				}catch (DateTimeParseException e ) {
					try{DateTimeFormatter formatter;
					formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();
					// you can change format of LocalDate
					localDate =LocalDate.parse(str_date, formatter);
					}catch (DateTimeParseException e2 ) {
						logger.error(e.getMessage());
					}
				}
			}
		}
		return localDate;
	}

}
