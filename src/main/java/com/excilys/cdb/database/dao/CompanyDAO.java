package com.excilys.cdb.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.core.QCompany;
import com.excilys.cdb.database.core.QComputer;
import com.excilys.cdb.database.mapperdao.CompanyMapper;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository

public class CompanyDAO {
	Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	@PersistenceContext
	private EntityManager entityManager;
	String selectAllCompany= "SELECT * FROM company";
	String selectCount= "SELECT count(*)FROM company ";
	private final String selectCountCompany ="SELECT COUNT(*) from company WHERE id=?";
	String deleteCompany = "DELETE FROM company WHERE id=?";
	@Autowired
	private  ComputerDAO computerDao;
	@Autowired
	private  CompanyMapper companyMapper;

	private static QCompany qCompany = QCompany.company;
	private static QComputer qComputer = QComputer.computer;

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
		JPAQuery query = new JPAQuery(entityManager);
		List <Company> listCompany = query.from(qCompany).list(qCompany);
		return listCompany;

	}

	public  int getSizeCompany() {
		JPAQuery query = new JPAQuery(entityManager);
		int sizeTable = (int) query.from(qCompany).count();
		return sizeTable;
	}


	public  int getSizeCompanyId(Long companyId) {
		JPAQuery query = new JPAQuery(entityManager);
		int sizeTable = (int) query.from(qCompany)
									.where(qCompany.id.eq(Long.valueOf(companyId)))
									.count();
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
