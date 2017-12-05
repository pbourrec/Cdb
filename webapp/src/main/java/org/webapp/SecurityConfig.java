package org.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  @Bean
	  public UserDetailsService userDetailsService() {
	 InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
	 manager.createUser(User.withDefaultPasswordEncoder().username("admin").password("adminpassword").roles("ADMIN").build());
	 manager.createUser(User.withDefaultPasswordEncoder().username("Don PAblo De la Vega").password("password").roles("ADMIN").build());
	 return manager;
	  }
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  http
		  .authorizeRequests()
		   .anyRequest().authenticated()
		   .and()
		  .formLogin()
		   .and()
		  .httpBasic();
		}


	  @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	  }
}