package com.excilys.project.database.controller;

import com.excilys.project.database.dao.DatabaseVerify;

public class ConditionControl {
	
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
			isOK=DatabaseVerify.isIdOkCompany(longOK);

		}
		return isOK;
	}

}
