package org.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.model.Company;
import org.model.CompanyDTO;
import org.model.ComputerDTO;
import org.validator.DataValidation;


@Component
public class CompanyMapper {
	 Scanner sc = new Scanner(System.in);
	 @Autowired
	 private  DataValidation dataControl;

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
		String computerManufacturer = sc.nextLine();
		Long idCompany = dataControl.stringToLongIDCompany( computerManufacturer, sc);
		return idCompany;
	}

}
