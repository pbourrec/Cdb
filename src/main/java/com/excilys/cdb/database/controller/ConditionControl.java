package com.excilys.cdb.database.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.dao.DatabaseVerify;

@Component
public class ConditionControl {
	Logger logger = LoggerFactory.getLogger(ConditionControl.class);
	@Autowired
	private  ControlFormat controlFormat;
	@Autowired
	private  DatabaseVerify databaseVerify;

	public  boolean isIdCompanyValid(String id){
		boolean isOK=false;
		Long longOK = controlFormat.stringTolong(id);
		if (longOK==null){
			isOK=false;
		}else{
			isOK=databaseVerify.isIdOkCompany(longOK);

		}
		return isOK;
	}

	public  boolean isIdComputerValid(String id){
		boolean isOK=false;
		Long longOK = controlFormat.stringTolong(id);
		if (longOK==null){
			isOK=false;
		}else{
			isOK=databaseVerify.isIdOkComputer(longOK);

		}
		return isOK;
	}

}
