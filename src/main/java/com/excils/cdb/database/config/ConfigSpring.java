package com.excils.cdb.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.excilys.cdb.database.controller",
				"com.excilys.cdb.database.dao",
				"com.excilys.cdb.database.ihm",
				"com.excilys.cdb.database.mapper",
				"com.excilys.cdb.database.service",
				"com.excilys.cdb.database.servlets"})
public class ConfigSpring {

}
