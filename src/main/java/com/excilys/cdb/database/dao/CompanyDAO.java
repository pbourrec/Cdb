package com.excilys.cdb.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.mapperdao.CompanyMapper;

@Repository

public class CompanyDAO {
	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

	String selectAllCompany= "SELECT * FROM company";
	String selectCount= "SELECT count(*)FROM company ";
	private final String selectCountCompany ="SELECT COUNT(*) from company WHERE id=?";
	String deleteCompany = "DELETE FROM company WHERE id=?";
	@Autowired
	private  ComputerDAO computerDao;
	@Autowired
	private  CompanyMapper companyMapper;

	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;

	@Autowired 
	public CompanyDAO(DataSource datasource, PlatformTransactionManager transactionManager) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.transactionManager =  transactionManager;
	}




	public class RowMapperCompany implements RowMapper{
		@Override
		public Company mapRow(ResultSet rsCompany, int arg1) throws SQLException {
			return companyMapper.rsToCompany(rsCompany);
		}

	}
	/**
	 * 
	 * @return ResultSet rs Resultat de la query sur la table "company"
	 */
	public  List<Company> getCompany() {
		List <Company> listCompany = new ArrayList<>();
		listCompany = jdbcTemplate.query(selectAllCompany, new RowMapperCompany());
		return listCompany;

	}
	
	public  int getSizeCompany() {
		int sizeTable =jdbcTemplate.queryForObject(selectCount, Integer.class);
		return sizeTable;
	}
	

	public  int getSizeCompanyId(Long companyId) {
		int sizeTable =jdbcTemplate.queryForObject(selectCountCompany, 
													new Object[] {companyId},
													Integer.class);
		return sizeTable;
	}
	
	
	public void deleteCompany(Long companyId) throws SQLException {
	      TransactionStatus ts =transactionManager.getTransaction(new DefaultTransactionDefinition());
		try{
			computerDao.deleteWithCompany(companyId);
			jdbcTemplate.update(deleteCompany, companyId);
			transactionManager.commit(ts);
		}catch (Exception e) {
			transactionManager.rollback(ts);
			logger.error("error in deleteCompany or ComputerDAO");
		}
	}


}
