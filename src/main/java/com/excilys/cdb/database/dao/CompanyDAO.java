package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.mapper.CompanyMapper;

@Component
public class CompanyDAO {
	 Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	  String selectAllCompany= "SELECT * FROM company";
	  String selectCount= "SELECT count(*)FROM company ";
	  String deleteCompany = "DELETE FROM company WHERE id=?";
	@Autowired
	private  ComputerDAO computerDao;
	@Autowired
	private  CompanyMapper companyMapper;
	@Autowired
	private  DatabaseConn databaseConn;

	/**
	 * 
	 * @return ResultSet rs Resultat de la query sur la table "company"
	 */
	public  List<Company> getCompany() {
		List <Company> listCompany = new ArrayList<>();
		ResultSet rs = null;
		try(		Connection conn =databaseConn.databaseConnection(); PreparedStatement prepstmt = databaseConn.databasePrepStatement(conn, selectAllCompany)) {
			rs = prepstmt.executeQuery();
			while (rs.next()){
				listCompany.add(companyMapper.rsToCompany(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return listCompany;

	}

	public  int getSizeCompany() {
		ResultSet rs = null;
		int sizeTable = 0;
		try(Connection conn =databaseConn.databaseConnection(); PreparedStatement prepstmt = databaseConn.databasePrepStatement(conn,selectCount)) {
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

	public void deleteCompany(Long companyId) throws SQLException {
		Connection conn =databaseConn.databaseConnection(); 

		try(PreparedStatement prepstmt = databaseConn.databasePrepStatement(conn,deleteCompany)) {
			conn.setAutoCommit(false);
			computerDao.deleteWithCompany(conn,companyId);
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
