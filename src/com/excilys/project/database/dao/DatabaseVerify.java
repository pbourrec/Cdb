package com.excilys.project.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseVerify {


	private final static String selectCountCompany ="SELECT COUNT(*) from company WHERE id=?";
	private final static String selectCountComputer ="SELECT COUNT(*) from computer WHERE id=?";
	/**
	 * 
	 * @param stmt Statement
	 * @param computerManufacturer ID du fabricant voulu
	 * @return boolean isOK le boolean sera vrai si la table "company" contient bien l'ID du fabricant voulu
	 */
	public static boolean isIdOkCompany(Long computerManufacturer){
		boolean isOK = false;
		// si computerManufacturer est null, on considère que l'utilisateur ne veut pas déclarer le fabricant de l'ordinateur
		if(!computerManufacturer.equals("")){
			try (PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(selectCountCompany)){
				//On regarde la TAILLE de la query. Si on a 1 résultat, alors l'ID est bon
				prepstmt.setLong(1,computerManufacturer);
				ResultSet rs= prepstmt.executeQuery();
				rs.next();
				if(rs.getLong(1)==1){
					isOK = true;
				}	
			} catch ( SQLException e2) {
				e2.printStackTrace();
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
	public static boolean isIdOkComputer(Long computerId){
		boolean isOK = false;
		try (PreparedStatement prepstmt = DatabaseConn.databasePrepStatement(selectCountComputer)){
			//On regarde la TAILLE de la query. Si on a 1 résultat, alors l'ID est bon
			prepstmt.setLong(1,computerId);
			ResultSet rs= prepstmt.executeQuery();
			rs.next();
			if(rs.getLong(1)==1){
				isOK = true;
			}else {
				System.out.println("l'ID rentré n'existe pas, veuillez recommencer");
			}
		} catch (NumberFormatException e1) {
			System.out.println("l'ID rentré n'est pas sous le bon format, veuillez recommencer");
		} catch ( SQLException e2) {
			System.out.println("l'ID rentré n'existe pas, veuillez recommencer");
		}
		return isOK;
	}

}
