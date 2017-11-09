package com.excilys.cdb.database.dao;

import java.sql.Connection;
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
	final static String deleteCompany = "DELETE FROM company WHERE id=?";

	/**
	 * 
	 * @return ResultSet rs Resultat de la query sur la table "company"
	 */
	public static List<Company> getCompany() {
		List <Company> listCompany = new ArrayList<>();
		ResultSet rs = null;
		try(		Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn, selectAllCompany)) {
			rs = prepstmt.executeQuery();
			while (rs.next()){
				listCompany.add(CompanyMapper.rsToCompany(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return listCompany;

	}

	public static int getSizeCompany() {
		ResultSet rs = null;
		int sizeTable = 0;
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,selectCount)) {
			rs = prepstmt.executeQuery();
			rs.next();
			sizeTable = rs.getInt(1);
			prepstmt.execute();
			rs.close();

		} catch (SQLException e) {
			logger.error(e.getMessage());

		}
		return sizeTable;

	}

	public static void deleteCompany(Long companyId) throws SQLException {
		Connection conn =DatabaseConn.databaseConnection(); 

		try(PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,deleteCompany)) {
			conn.setAutoCommit(false);
			ComputerDAO.deleteWithCompany(conn,companyId);
			prepstmt.setLong(1,companyId);
			prepstmt.execute();
			conn.commit();
			logger.error("reussite de la suppression, il n'y a plus que " + getSizeCompany() + "companies ");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			conn.rollback();
			throw e;
		}finally {
			conn.close();
		}
	}


}
