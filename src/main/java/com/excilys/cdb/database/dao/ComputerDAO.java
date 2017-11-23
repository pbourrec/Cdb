package com.excilys.cdb.database.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.QCompany;
import com.excilys.cdb.database.core.QComputer;
import com.excilys.cdb.database.mapperdao.ComputerMapper;
import com.mysema.query.jpa.hibernate.HibernateQueryFactory;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
@SuppressWarnings("unchecked")
public class ComputerDAO {
	Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	@Autowired
	private  ComputerMapper computerMapper;

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	private static QCompany qCompany = QCompany.company;
	private static QComputer qComputer = QComputer.computer;

	@Autowired 
	public ComputerDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

    private SessionFactory sessionFactory;
    Session session = entityManager.unwrap(Session.class);
    private Supplier<HibernateQueryFactory> queryFactory =
            () -> new HibernateQueryFactory((Provider<Session>) sessionFactory.getCurrentSession());

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
}
	public class RowMapperComputer implements RowMapper{
		@Override
		public Computer mapRow(ResultSet rsComputer, int arg1) throws SQLException {
			return computerMapper.rsToComputer(rsComputer);
		}

	}


	String insertComputer = "INSERT INTO computer(name,introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private final String updateComputer = "UPDATE computer SET name =?,introduced= ?, discontinued= ?, company_id = ? WHERE id=?";
	private final String  deleteComputer = "DELETE FROM computer  WHERE id=?";
	private final String queryComputer = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id=?";
	private final String queryComputerByName = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ?";
	private final String queryComputerByCompanyName = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ?";
	private final String queryComputerByCompanyId = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.id=?";
	private final String deleteComputerByCompanyId = "DELETE FROM computer WHERE computer.company_id=?";
	private final  String selectCountComputer ="SELECT COUNT(*) from computer WHERE id=?";

	String selectAllComputer= "SELECT * FROM computer ";
	private   String selectAllComputerPagination= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?";
	String selectCount= "SELECT count(*)FROM computer ";

	/**
	 * 
	 * @param computer Nouvel ordinateur à ajouter a la database
	 */
	public  boolean upload(Computer computer) {
		jdbcTemplate.update(insertComputer, computer.getComputerName(),
				(computer.getDateIntroduced()!=null ?  Date.valueOf(computer.getDateIntroduced()) : null ),
				(computer.getDateDiscontinued()!=null ?  Date.valueOf(computer.getDateDiscontinued()) : null ),
				computer.getCompany().getId());
		return true;
	}

	/**
	 * 
	 * @param newComputer Computer a updater
	 * @param idComputer ID du de l'ordinateur a updater
	 */
	public  void update(Computer newComputer, Long idComputer){
		jdbcTemplate.update(updateComputer, newComputer.getComputerName(),
				(newComputer.getDateIntroduced()!=null ?  Date.valueOf(newComputer.getDateIntroduced()) : null ),
				(newComputer.getDateDiscontinued()!=null ?  Date.valueOf(newComputer.getDateDiscontinued()) : null ),
				newComputer.getCompany().getId(),
				idComputer);

	}

	/**
	 * 
	 * @param idComputer ID de l'ordinateur a supprimer
	 */
	public  void delete(Long idComputer) {
		jdbcTemplate.update(deleteComputer, idComputer);
	}

	/**
	 * @param computerID Id de l'ordinateur a rechercher
	 * @return Computer computerQueried
	 */
	public  Computer queryOne(Long computerID){
		List<Computer> listComputer = new ArrayList<>(); 
		if (computerID!=0L) {
			listComputer = jdbcTemplate.query(queryComputer,
					new Object[] {computerID}, 
					new RowMapperComputer());

		}
		Computer computerQueried = listComputer.get(0);
		return computerQueried;
	}


	/**
	 * 
	 * @return ResultSet rs Resultat de la query sur la table "computer"
	 */
	public  List<Computer> getAllComputer() {
		QComputer qComputer = QComputer.computer;
		JPAQuery query = new JPAQuery(entityManager);
		List<Computer> listComputer =query.from(qComputer).list(qComputer);
		List<Computer> listComputer2 =queryFactory.get().from(qComputer).list(qComputer);
		logger.info("JPAQuery passed");
		return listComputer;

	}

	public  List<Computer> getComputerPagination(Long offSet, Long limit) {
		List<Computer> listComputer =  queryFactory.get()
													.from(qComputer)
													.leftJoin(qCompany)
													.on(qCompany.id.eq(qComputer.company.id))
													.limit(limit)
													.offset(offSet)
													.fetch()
													.list(qComputer);
		return listComputer;
	}

	/**
	 * 
	 * @return int sizeTable Taille de la table "computer"
	 */
	public  int getSizeComputer() {
		QComputer qComputer = QComputer.computer;
		JPAQuery query = new JPAQuery(entityManager);
		int sizeTable =(int) query.from(qComputer).count();
		return sizeTable;
	}

	public  int getSizeComputerId(Long computerId) {
		int sizeTable =jdbcTemplate.queryForObject(selectCountComputer, 
				new Object[] {computerId},
				Integer.class);
		return sizeTable;
	}



	public  List<Computer> getComputerByName(String nameToFind) {
		nameToFind="%"+nameToFind+"%";
		List<Computer> listComputer = new ArrayList<>(); 
		listComputer = jdbcTemplate.query(queryComputerByName,
				new Object[] {nameToFind},
				new RowMapperComputer());

		return listComputer;
	}

	public  List<Computer> getComputerByCompany(String companyToFind) {
		companyToFind="%"+companyToFind+"%";
		List<Computer> listComputer = new ArrayList<>(); 
		listComputer = jdbcTemplate.query(queryComputerByCompanyName,
				new Object[] {companyToFind},
				new RowMapperComputer());

		return listComputer;
	}

	public  void deleteWithCompany( Long companyId) {
		jdbcTemplate.update(deleteComputerByCompanyId, companyId);
	}

	public  List<Computer> getComputerByCompanyId(Long companyIdToFind) {
		List<Computer> listComputer = new ArrayList<>(); 
		listComputer = jdbcTemplate.query(queryComputerByCompanyId,
				new Object[] {companyIdToFind},
				new RowMapperComputer());
		return listComputer;
	}
}
