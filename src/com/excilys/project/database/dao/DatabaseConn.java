package com.excilys.project.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseConn {
	

	//connection à la database
	public static Connection databaseConnection() {
		try {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepstmt;
	
	}

}
