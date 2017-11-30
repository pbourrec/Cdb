package com.excilys.cdb.database.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ConfigSpring.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { ConfigSpring.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/dashboard", "/addComputer", "/deleteComputer", "/editComputer" };
	}



}