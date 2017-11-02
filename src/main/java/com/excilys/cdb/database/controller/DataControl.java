package com.excilys.cdb.database.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataControl {
	static Logger logger = LoggerFactory.getLogger(DataControl.class);

	/**
	 * 
	 * @param conn Connection établie au début
	 * @param computerManufacturer ID du fabricant
	 * @param sc Scanner input clavier
	 * @return Long ValueOf ID du fabricant
	 */
	public static Long stringToLongIDCompany(String computerManufacturer, Scanner sc ){
		logger.debug("Conversion String vers id");
		//dans le cas ou l'entrée est nulle, on ne prendra pas de constructeur
		if(!computerManufacturer.equals("")){
			//tant que l'ID n'existe pas ou n'est pas valide
			while (!ConditionControl.isIdCompanyValid(computerManufacturer)) {
				System.out.println("l'ID rentré n'existe pas ou n'est pas sous le bon format, merci de reessayer");
				computerManufacturer = sc.nextLine();
			}
			return Long.valueOf(computerManufacturer);
		}
		else {return 0l;}	
	}
	/**
	 * 
	 * @param conn Connection établie au début
	 * @param computerID ID de l'ordinateur
	 * @param sc Scanner input clavier
	 * @return Long valueOf ID de l'ordinateur
	 */
	public static Long stringToLongIDComputer(String computerID, Scanner sc ){

		//tant que l'ID n'existe pas ou n'est pas valide
		while (!ConditionControl.isIdComputerValid(computerID)) {
			System.out.println("l'ID rentré n'existe pas ou n'est pas sous le bon format, merci de reessayer");
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
	public static Integer stringToInt(String stringToInt, Scanner sc ){
		boolean isOK= false;
		//On affiche 50 pages par défaut
		Integer intReturned=50 ;
		while (!isOK) {
			try{
				intReturned= Integer.valueOf(stringToInt);
				isOK = true;

			} catch (NumberFormatException nfe) {
				System.out.println("La valeur rentrée n'est pas valable, merci de recommencer");
				stringToInt = sc.nextLine();
				isOK=false;
			}
		}
		return intReturned;
	}

	/**
	 * 
	 * @param str_date String à convertir en date
	 * @return java.sql.Date Date issue de la string
	 */
	public static java.sql.Date convertStringToTimestamp(String str_date) {
		java.sql.Date timeStampDate = null;
		try {
			//creation du format de la date
			DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			// you can change format of date
			Date date = formatter.parse(str_date);
			System.out.println(str_date);
			timeStampDate = new java.sql.Date(date.getTime());
			System.out.println(timeStampDate);
			return timeStampDate;

		} catch (ParseException e) {
			System.out.println("Mauvais format de date, veuillez recommencer");
			return timeStampDate;
		}
	}

}