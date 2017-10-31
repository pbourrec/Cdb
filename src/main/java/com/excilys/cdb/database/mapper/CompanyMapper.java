package com.excilys.cdb.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.database.datatype.Company;

public class CompanyMapper {
	
	public static Company rsToCompany(ResultSet rsCompany){
		Company companyConvert = new Company();
		try {
			companyConvert.setId(rsCompany.getLong(1));
			companyConvert.setName(rsCompany.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return companyConvert;		
	}

}
