package org.webapp;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	  public void onStartup(final ServletContext container) throws ServletException {
	    final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	    ctx.register(ConfigSpring.class);
	    ctx.setServletContext(container);
	    final ServletRegistration.Dynamic servlet = container.addServlet("dispatcherServlet", new DispatcherServlet(ctx));
	    servlet.setLoadOnStartup(1);
	    servlet.addMapping("/dashboard",
				"/addComputer",
				"/editComputer",
				"/deleteCompany"
				);
	}




}