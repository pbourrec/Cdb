package com.excilys.cdb.database.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ControlFormat {
	 Logger logger = LoggerFactory.getLogger(ControlFormat.class);

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
