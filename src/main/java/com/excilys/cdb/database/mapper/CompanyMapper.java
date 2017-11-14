package com.excilys.cdb.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.controller.DataControl;
import com.excilys.cdb.database.datatype.Company;


@Component
public class CompanyMapper {
	 Scanner sc = new Scanner(System.in);
	 @Autowired
	 private  DataControl dataControl;

	public  Company rsToCompany(ResultSet rsCompany){
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

	public  Long enterCompanyId() {
		System.out.println("Quel sera le constructeur de l'ordinateur (choisir un ID)" );
		String computerManufacturer = sc.nextLine();
		Long idCompany = dataControl.stringToLongIDCompany( computerManufacturer, sc);
		return idCompany;
	}

}
