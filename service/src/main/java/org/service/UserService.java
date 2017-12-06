package org.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
	public class UserService implements UserDetailsService{
		private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	    private  UserRepository userRepository;

		public UserService(UserRepository ur) {
			LOGGER.info("build user service");
			userRepository=ur;
		}
		
		@Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			LOGGER.info("loaddd user");
			org.model.User user = userRepository.findByLogin(username);
			LOGGER.info(user.toString());
			LOGGER.info(user.getPassword());
			Set<String> set= new HashSet<>();
			set.add(user.getRole());
	        return new org.springframework.security.core.userdetails.User(user.getLogin(),
	                                                                      user.getPassword(),
	                                                                      set.stream()
	                                                                      				.map(x -> new SimpleGrantedAuthority(x))
	                                                                      				.collect(Collectors.toSet())
	                                                                     );
	}
}
