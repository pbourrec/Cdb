package com.excilys.cdb.database.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.excilys.cdb.database.validator",
	"com.excilys.cdb.database.dao",
	"com.excilys.cdb.database.dao.jpadata",
	"com.excilys.cdb.database.core",
	"com.excilys.cdb.database.mapperdto",
	"com.excilys.cdb.database.mapperdao",
	"com.excilys.cdb.database.service",
	"com.excilys.cdb.database.controller",
	})
@Import(ConfigSpringDataJpa.class)
public class ConfigSpring {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		return messageSource;
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/");
		bean.setSuffix(".jsp");
		return bean;
}
}
