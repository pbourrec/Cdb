package com.excilys.cdb.database.validator;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FormatValidation {
	 Logger logger = LoggerFactory.getLogger(FormatValidation.class);

		public  Long stringTolong(String stringToConv) {
			Long longConverted=null;
			try{
			 longConverted = Long.valueOf(stringToConv);
			 logger.debug("{}",longConverted);
			} catch (NumberFormatException e){
				 longConverted =null;
			}
			return longConverted;
		}
		
}
