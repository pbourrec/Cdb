package com.excilys.cdb.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:spring.properties")
public class DatabaseConn {

	@Value("${spring.datasource.url}")
	private String datasourceURl;
	@Value("${spring.datasource.username}")
	private  String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;

	
	@Autowired
	public	HikariDataSource ds;
	
	
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driverClass);
		config.setUsername(username);
		config.setPassword(password);
		config.setJdbcUrl( datasourceURl);
		ds = new HikariDataSource(config);
		return ds;
	}

	
	Logger logger = LoggerFactory.getLogger(DatabaseConn.class);
	//connection à la database
	public  Connection databaseConnection() {
		try {

			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() + " Error sql connection");
		}
	}
}
