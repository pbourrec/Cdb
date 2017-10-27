package com.excilys.project.database.controller;

public class ControlFormat {

		public static Long stringTolong(String stringToConv) {
			Long longConverted=null;
			try{
			 longConverted = Long.valueOf(stringToConv);
			} catch (NumberFormatException e){
				 longConverted =null;
			}
			return longConverted;
		}
		
}
