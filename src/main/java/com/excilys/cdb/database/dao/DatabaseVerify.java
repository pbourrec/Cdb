package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseVerify {


	private final  String selectCountCompany ="SELECT COUNT(*) from company WHERE id=?";
	private final  String selectCountComputer ="SELECT COUNT(*) from computer WHERE id=?";
	Logger logger = LoggerFactory.getLogger(DatabaseVerify.class);

	
	@Autowired
	private  DatabaseConn databaseConn;

	/**
	 * 
	 * @param stmt Statement
	 * @param computerManufacturer ID du fabricant voulu
	 * @return boolean isOK le boolean sera vrai si la table "company" contient bien l'ID du fabricant voulu
	 */
	public  boolean isIdOkCompany(Long computerManufacturer){
		boolean isOK = false;
		// si computerManufacturer est null, on considère que l'utilisateur ne veut pas déclarer le fabricant de l'ordinateur
		if(computerManufacturer != null){
			try(Connection conn =databaseConn.databaseConnection(); PreparedStatement prepstmt = databaseConn.databasePrepStatement(conn,selectCountCompany)){
				//On regarde la TAILLE de la query. Si on a 1 résultat, alors l'ID est bon
				prepstmt.setLong(1,computerManufacturer);
				ResultSet rs= prepstmt.executeQuery();
				rs.next();
				if(rs.getLong(1)==1){
					isOK = true;
				}	
				rs.close();
			} catch ( SQLException e2) {
				logger.error(e2.getMessage());
			}
		} else{
			isOK=true;
		}
		return isOK;
	}

	/**
	 * 
	 * @param stmt Statement
	 * @param computer ID ID de l'ordinateur voulu
	 * @return boolean isOK le boolean sera vrai si la table "computer" contient bien l'ID de l'ordinateur voulu
	 */
	public  boolean isIdOkComputer(Long computerId){
		boolean isOK = false;
		try(Connection conn =databaseConn.databaseConnection(); PreparedStatement prepstmt = databaseConn.databasePrepStatement(conn,selectCountComputer)){
			//On regarde la TAILLE de la query. Si on a 1 résultat, alors l'ID est bon
			prepstmt.setLong(1,computerId);
			ResultSet rs= prepstmt.executeQuery();
			rs.next();
			if(rs.getLong(1)==1){
				isOK = true;
			}else {
				System.out.println("l'ID rentré n'existe pas, veuillez recommencer");
			}
			rs.close();
		} catch (NumberFormatException e1) {
			System.out.println("l'ID rentré n'est pas sous le bon format, veuillez recommencer");
		} catch ( SQLException e2) {
			System.out.println("l'ID rentré n'existe pas, veuillez recommencer");
		}
		return isOK;
	}

}
