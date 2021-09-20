package com.jaggu.spring.boot.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaggu.spring.boot.auth.model.ZerocodeUser;
import com.jaggu.spring.boot.auth.repository.JagguUserRepository;

import java.util.Arrays;

@Service("userService")
public class JagguUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(JagguUserDetailsService.class);
	
	@Autowired
	private JagguUserRepository jagguUserRepository;

	public JagguUserDetailsService() {}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("getting user details for user [{}]", username);
		log.info("Users List [{}]",jagguUserRepository.findAll());
		// TODO: Need to write login code here
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE");
		return new ZerocodeUser(
				username,
				"37dba13720bf1b760fd043d098f55396",
				Boolean.TRUE,
				Boolean.TRUE,
				Boolean.TRUE,
				Boolean.TRUE,
				Arrays.asList(authority),
				"firstname",
				"lastname");
	}
	
}
