package com.excilys.cdb.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.cdb.database.controller.DataControl;
import com.excilys.cdb.database.datatype.Company;

public class CompanyMapper {
	static Scanner sc = new Scanner(System.in);

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

	public static Long enterCompanyId() {
		System.out.println("Quel sera le constructeur de l'ordinateur (choisir un ID)" );
		String computerManufacturer = sc.nextLine();
		Long idCompany = DataControl.stringToLongIDCompany( computerManufacturer, sc);
		return idCompany;
	}

}
