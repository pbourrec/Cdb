package org.validator;




import org.persistence.DatabaseVerify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConditionValidation {
	Logger logger = LoggerFactory.getLogger(ConditionValidation.class);
	@Autowired
	private  FormatValidation controlFormat;
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
