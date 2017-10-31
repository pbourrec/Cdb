package com.excilys.cdb.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.database.datatype.Computer;

public class ComputerMapper {

	
	public static Computer rsToComputer(ResultSet rsComputer){
		Computer computerConvert = new Computer();
		try {
			
			computerConvert.setId(rsComputer.getLong(1));
			computerConvert.setComputerName(rsComputer.getString(2));
			computerConvert.setDateIntroduced(rsComputer.getDate(3));
			computerConvert.setDateDiscontinued(rsComputer.getDate(4));
			computerConvert.setComputerManufacturer(rsComputer.getLong(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return computerConvert;		
	}
}
