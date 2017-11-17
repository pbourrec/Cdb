package com.excils.cdb.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.excilys.cdb.database.dao.DatabaseConn;

@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.cdb.database.controller",
	"com.excilys.cdb.database.dao",
	"com.excilys.cdb.database.ihm",
	"com.excilys.cdb.database.mapper",
	"com.excilys.cdb.database.service",
	"com.excilys.cdb.database.mvcservlets",
"com.excilys.cdb.database.servlets"})

public class ConfigSpring extends WebMvcConfigurerAdapter {
	
	@Autowired  
	private DatabaseConn databaseConn;
	
	
	@Bean
	  public PlatformTransactionManager transactionManager() {
	      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	      transactionManager.setDataSource(databaseConn.ds);
	      return transactionManager;
	  }
	

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/dashboard");
}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
