package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;

public class ComputerDAO {
	static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	final static String insertComputer = "INSERT INTO computer(name,introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private final static String updateComputer = "UPDATE computer SET name =?,introduced= ?, discontinued= ?, company_id = ? WHERE id=?";
	final static String deleteComputer = "DELETE FROM computer  WHERE id=?";
	private final static String queryComputer = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id=?";
	private final static String queryComputerByName = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ?";
	private final static String queryComputerByCompanyName = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ?";
	private final static String queryComputerByCompanyId = "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.id=?";
	private final static String deleteComputerByCompanyId = "DELETE FROM computer WHERE computer.company_id=?";

	final static String selectAllComputer= "SELECT * FROM computer ";
	private final static String selectAllComputerPagination= "SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?";
	final static String selectCount= "SELECT count(*)FROM computer ";

	/**
	 * 
	 * @param conn Connection 
	 * @param computer Nouvel ordinateur à ajouter a la database
	 */
	public static boolean upload(Computer computer) {
		//Try with ressource avec création d'un statement qui sera close a la fin du try
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,insertComputer)) {
			//Creation de la string a utiliser dans la query
			prepstmt.setString(1,computer.getComputerName());
			prepstmt.setDate(2,(computer.getDateIntroduced()!=null ?  Date.valueOf(computer.getDateIntroduced()) : null ));
			prepstmt.setDate(3, (computer.getDateDiscontinued()!=null ?  Date.valueOf(computer.getDateDiscontinued()) : null ));
			prepstmt.setLong(4, computer.getCompany().getId());
			logger.debug(prepstmt.toString());
			prepstmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/**
	 * 
	 * @param conn Connection 
	 * @param newComputer Computer a updater
	 * @param idComputer ID du de l'ordinateur a updater
	 */
	public static void update(Computer newComputer, Long idComputer){
		//Try with ressource avec création d'un statement qui sera close a la fin du try
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,updateComputer)) {
			//Creation de la string a utiliser dans la query
			prepstmt.setString(1,newComputer.getComputerName());
			prepstmt.setDate(2,(newComputer.getDateIntroduced()!=null ?  Date.valueOf(newComputer.getDateIntroduced()) : null ));
			prepstmt.setDate(3, (newComputer.getDateDiscontinued()!=null ?  Date.valueOf(newComputer.getDateDiscontinued()) : null ));
			prepstmt.setLong(4, newComputer.getCompany().getId());
			prepstmt.setLong(5,idComputer);
			logger.debug(prepstmt.toString());
			prepstmt.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());

		}
	}

	/**
	 * 
	 * @param conn Connection 
	 * @param idComputer ID de l'ordinateur a supprimer
	 */
	public static void delete(Long idComputer) {

		//Try with ressource avec création d'un statement qui sera close a la fin du try
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,deleteComputer)) {
			prepstmt.setLong(1,idComputer);
			prepstmt.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 
	 * @param conn Connection
	 * @param computerID Id de l'ordinateur a rechercher
	 * @return ResultSet rs resultat de la query sur la table 'computer avec l'ID voulu
	 */
	public static Computer queryOne(Long computerID){
		ResultSet rs = null;
		Computer computerQueried = new Computer();
		if (computerID!=0L) {
			try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,queryComputer)){
				prepstmt.setLong(1,computerID);
				rs = prepstmt.executeQuery();
				rs.next();
				computerQueried = ComputerMapper.rsToComputer(rs);
				rs.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
		return computerQueried;

	}


	/**
	 * 
	 * @param conn Connection
	 * @return ResultSet rs Resultat de la query sur la table "computer"
	 */
	public static List<Computer> getAllComputer() {
		ResultSet rs = null;
		List<Computer> listComputer = new ArrayList<>(); 
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,selectAllComputer)) {
			rs = prepstmt.executeQuery();
			logger.debug(prepstmt.toString());
			while(rs.next()){
				listComputer.add(ComputerMapper.rsToComputer(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return listComputer;

	}

	public static List<Computer> getComputerPagination(Long offSet, Long limit) {
		List <Computer> listComputer = new ArrayList<>();
		ResultSet rs = null;

		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,selectAllComputerPagination)) {
			prepstmt.setLong(1, limit);
			prepstmt.setLong(2, offSet);
			rs = prepstmt.executeQuery();
			while (rs.next()){
				listComputer.add(ComputerMapper.rsToComputer(rs));
			}
			rs.close();
		} catch (SQLException e) {
			//			CompanyDAO.logger.error(e.getMessage());
		}
		return listComputer;

	}

	/**
	 * 
	 * @param conn Connection
	 * @return int sizeTable Taille de la table "computer"
	 */
	public static int getSizeComputer() {
		ResultSet rs = null;
		int sizeTable = 0;
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,selectCount)) {
			rs = prepstmt.executeQuery();
			rs.next();
			sizeTable = rs.getInt(1);
			prepstmt.execute();
			rs.close();
		} catch (SQLException e) {
			logger.error(
					e.getMessage());
		}
		return sizeTable;
	}

	public static List<Computer> getComputerByName(String nameToFind) {

		ResultSet rs = null;
		List<Computer> listComputer = new ArrayList<>(); 
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,queryComputerByName)) {
			nameToFind="%"+nameToFind+"%";
			prepstmt.setString(1, nameToFind);
			rs = prepstmt.executeQuery();
			logger.debug(prepstmt.toString());
			while(rs.next()){
				listComputer.add(ComputerMapper.rsToComputer(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Erreur dans la query par nom");
		}
		return listComputer;
	}

	public static List<Computer> getComputerByCompany(String companyToFind) {
		ResultSet rs = null;
		List<Computer> listComputer = new ArrayList<>(); 
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,queryComputerByCompanyName)) {
			companyToFind="%"+companyToFind+"%";
			prepstmt.setString(1, companyToFind);
			rs = prepstmt.executeQuery();
			logger.debug(prepstmt.toString());
			while(rs.next()){
				listComputer.add(ComputerMapper.rsToComputer(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Erreur dans la query par nom");
		}
		return listComputer;
	}

	public static void deleteWithCompany(Connection conn, Long companyId) {
		try(PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,deleteComputerByCompanyId)) {
			prepstmt.setLong(1,companyId);
			prepstmt.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public static List<Computer> getComputerByCompanyId(Long companyIdToFind) {
		ResultSet rs = null;
		List<Computer> listComputer = new ArrayList<>(); 
		try(Connection conn =DatabaseConn.databaseConnection(); PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(conn,queryComputerByCompanyId)) {
			prepstmt.setLong(1, companyIdToFind);
			rs = prepstmt.executeQuery();
			logger.debug(prepstmt.toString());
			while(rs.next()){
				listComputer.add(ComputerMapper.rsToComputer(rs));
			}
			rs.close();
		} catch (SQLException e) {
			logger.error("Erreur dans la query par id");
		}
		return listComputer;
	}
}
