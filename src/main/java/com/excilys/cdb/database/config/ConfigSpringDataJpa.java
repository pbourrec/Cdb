package com.excilys.cdb.database.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("../../../resources/spring.properties")
@EnableJpaRepositories(basePackages = {"com.excilys.cdb.database.dao.jpadata"})
@ComponentScan({ "com.excilys.cdb.database.validator", "com.excilys.cdb.database.dao",
		"com.excilys.cdb.database.dao.jpadata", "com.excilys.cdb.database.ihm", "com.excilys.cdb.database.core",
		"com.excilys.cdb.database.mapperdto", "com.excilys.cdb.database.mapperdao", "com.excilys.cdb.database.service",
		"com.excilys.cdb.database.controller", "com.excilys.cdb.database.servlets" })
public class ConfigSpringDataJpa {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSpringDataJpa.class);
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "spring.datasource.driver-class-name";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "spring.datasource.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "spring.datasource.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "spring.datasource.username";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.url}")
	private String url;

	@Resource
	private Environment env;

	@Autowired
	private HikariDataSource ds;

	@Bean
	public HikariDataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setPassword(password);
		config.setUsername(username);
		config.setJdbcUrl(url);
		config.setDriverClassName(driverClass);
		ds = new HikariDataSource(config);
		return ds;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.excilys.cdb.database.core");

		Properties jpaProperties = new Properties();

		// Configures the used database dialect. This allows Hibernate to create SQL
		// that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));


		// If the value of this property is true, Hibernate writes all SQL
		// statements to the console.
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));


		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
