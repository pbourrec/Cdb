package com.excils.cdb.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.excilys.cdb.database.dao.DatabaseConn;

@Configuration
@ComponentScan({"com.excilys.cdb.database.controller",
	"com.excilys.cdb.database.dao",
	"com.excilys.cdb.database.ihm",
	"com.excilys.cdb.database.mapper",
	"com.excilys.cdb.database.service",
"com.excilys.cdb.database.servlets"})
public class ConfigSpring{
	@Autowired  
	private DatabaseConn databaseConn;
	
	
	@Bean
	  public PlatformTransactionManager transactionManager() {
	      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	      transactionManager.setDataSource(databaseConn.ds);
	      return transactionManager;
	  }
}
