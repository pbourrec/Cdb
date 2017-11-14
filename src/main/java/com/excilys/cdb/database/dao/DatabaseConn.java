package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DatabaseConn {
	 HikariConfig config = new HikariConfig("/hikari.properties");
	 HikariDataSource ds = new HikariDataSource(config);
	 Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	//connection à la database
	public  Connection databaseConnection() {
		try {

			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() + " Error sql connection");
		}
	}

	//Création d'un statement
	public  PreparedStatement databasePrepStatement(Connection conn, String sql) {
		PreparedStatement prepstmt = null;
		try{
			prepstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("erreur durant la connection a la base");
		}
		return prepstmt;
	}
}
