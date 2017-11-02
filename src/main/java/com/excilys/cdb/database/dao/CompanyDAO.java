package com.excilys.cdb.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.mapper.CompanyMapper;

public class CompanyDAO {
	static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	final static String selectAllCompany= "SELECT * FROM company";
	final static String selectCount= "SELECT count(*)FROM company ";

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
			logger.error("query succeded,  " + databaseGetSizeCompany() +"results returned");
			while (rs.next()){
				listCompany.add(CompanyMapper.rsToCompany(rs));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return listCompany;
		
	}
	
	public static int databaseGetSizeCompany() {
		ResultSet rs = null;
		int sizeTable = 0;
		try(PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(selectCount)) {
			rs = prepstmt.executeQuery();
			rs.next();
			sizeTable = rs.getInt(1);
			prepstmt.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());

		}
		return sizeTable;

	}


}