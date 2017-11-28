package com.excilys.cdb.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.cdb.database.validator",
	"com.excilys.cdb.database.dao",
	"com.excilys.cdb.database.dao.jpadata",
	"com.excilys.cdb.database.ihm",
	"com.excilys.cdb.database.core",
	"com.excilys.cdb.database.mapperdto",
	"com.excilys.cdb.database.mapperdao",
	"com.excilys.cdb.database.service",
	"com.excilys.cdb.database.controller",
"com.excilys.cdb.database.servlets"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.excilys.cdb.database.dao.jpadata")
public class ConfigSpring implements WebMvcConfigurer {
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
}
