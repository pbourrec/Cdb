package com.excilys.cdb.database.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.dao.DatabaseVerify;

public class ConditionControl {
	static Logger logger = LoggerFactory.getLogger(ConditionControl.class);
	
	public static boolean isIdCompanyValid(String id){
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
