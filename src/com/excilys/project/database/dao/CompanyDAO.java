package com.excilys.project.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.project.database.datatype.Company;
import com.excilys.project.database.mapper.CompanyMapper;

public class CompanyDAO {
	
	private final static String selectAllCompany= "SELECT * FROM company ";
	/**
	 * 
	 * @param conn Connection
	 * @return ResultSet rs Resultat de la query sur la table "company"
	 */
	public static List<Company> databaseGetCompany() {
		List <Company> listCompany = new ArrayList<>();
		ResultSet rs = null;
		try(PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(selectAllCompany)) {
			rs = prepstmt.executeQuery();
			while (rs.next()){
				listCompany.add(CompanyMapper.rsToCompany(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCompany;
	
	}

}
