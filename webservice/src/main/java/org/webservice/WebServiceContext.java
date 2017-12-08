package org.webservice;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.model",
		"org.persistence",
		"org.service",
		"org.validator", 
"org.webservice"})
public class WebServiceContext {

}