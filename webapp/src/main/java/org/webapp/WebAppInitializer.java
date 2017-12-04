package org.webapp;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer {


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(ConfigSpring.class);		
		context.setServletContext(servletContext);
		
		ServletRegistration.Dynamic dispatcher2 = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher2.setLoadOnStartup(1); 
		dispatcher2.addMapping("/dashboard",
				"/addComputer",
				"/editComputer",
				"/deleteCompany"
				);
	}




}