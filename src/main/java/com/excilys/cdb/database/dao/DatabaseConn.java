package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Driver;

public class DatabaseConn {
	static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);


	//connection à la database
	public static Connection databaseConnection() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(ComputerDAO.url, ComputerDAO.username, ComputerDAO.password);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() + " Error sql connection");
		}
	}

	//Création d'un statement
	public static PreparedStatement databasePrepStatement( String sql) {
		Connection conn =databaseConnection();
		PreparedStatement prepstmt = null;
		ResultSet rs = null;
		try {
			prepstmt = conn.prepareStatement(sql);
	
		} catch (SQLException e) {
			logger.error(e.getMessage());
			}
		return prepstmt;
	
	}

}
