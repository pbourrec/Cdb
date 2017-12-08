package org.webservice;


import javax.xml.ws.WebServiceContext;

import org.omg.IOP.ServiceContext;
import org.persistence.ConfigSpringDataJpa;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	  @Override
	  protected Class[] getRootConfigClasses() {
	    return new Class[] { WebServiceContext.class,ConfigSpringDataJpa.class,ServiceContext.class };
	  }

	  @Override
	  protected Class[] getServletConfigClasses() {
	    return null;
	  }

	  @Override
	  protected String[] getServletMappings() {
	    return new String[] { "/" };
	  }

}