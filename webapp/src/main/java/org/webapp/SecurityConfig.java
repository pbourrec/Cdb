package org.webapp;

import org.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired 
	private  UserService userService;
	
	
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(authenticationProvider());
		}


	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	  }
	  

		@Override
		@Bean
		public UserDetailsService userDetailsServiceBean() throws Exception {
			LOGGER.info("bean detail service");
			return userService;
		}

		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			LOGGER.info("password encoder");
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider
		      = new DaoAuthenticationProvider();
		    authProvider.setUserDetailsService(userService);
		    authProvider.setPasswordEncoder(passwordEncoder());
		    return authProvider;
	}
}