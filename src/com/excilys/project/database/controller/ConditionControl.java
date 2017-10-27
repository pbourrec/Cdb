package com.excilys.project.database.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.project.database.dao.DatabaseVerify;

public class ConditionControl {
	static Logger logger = LoggerFactory.getLogger(ConditionControl.class);
	
	public static boolean isIdCompanyValid(String id){
		logger.debug("Checking validity of ID");
		boolean isOK=false;
		Long longOK = ControlFormat.stringTolong(id);
		if (longOK==null){
			isOK=false;
		}else{
			isOK=DatabaseVerify.isIdOkCompany(longOK);
			
		}
		return isOK;
	}
	
	public static boolean isIdComputerValid(String id){
		logger.debug("Checking validity of ID");

		boolean isOK=false;
		Long longOK = ControlFormat.stringTolong(id);
		if (longOK==null){
			isOK=false;
		}else{
			isOK=DatabaseVerify.isIdOkComputer(longOK);

		}
		return isOK;
	}

}
