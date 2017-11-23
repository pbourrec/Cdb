package com.excils.cdb.database.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.excilys.cdb.database.dao.DatabaseConn;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.cdb.database.validator",
				"com.excilys.cdb.database.dao",
				"com.excilys.cdb.database.ihm",
				"com.excilys.cdb.database.core",
				"com.excilys.cdb.database.mapperdto",
				"com.excilys.cdb.database.mapperdao",
				"com.excilys.cdb.database.service",
				"com.excilys.cdb.database.controller",
				"com.excilys.cdb.database.servlets"})

public class ConfigSpring implements WebMvcConfigurer{

	@Autowired  
	private DatabaseConn databaseConn;
	
	@Autowired
	public	HikariDataSource ds;
	

	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(databaseConn.ds);
		return transactionManager;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/");
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
	
	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(ds);
	      
	      em.setPackagesToScan(new String[] { "com.excilys.cdb.database.core" });
	      em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      return em;
	   }
	  
	   @Bean 
	   public SessionFactory CreateSessionFactory() {
		   SessionFactory sessionFactory;
			   try {
				StandardServiceRegistry standardRegistry = 
			       new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				Metadata metaData = 
			        new MetadataSources(standardRegistry).getMetadataBuilder().build();
				 sessionFactory = metaData.getSessionFactoryBuilder().build();
			   } catch (Throwable th) {
				System.err.println("Enitial SessionFactory creation failed" + th);
				throw new ExceptionInInitializerError(th);
			  }
			   return sessionFactory;
			}
	   }
}
