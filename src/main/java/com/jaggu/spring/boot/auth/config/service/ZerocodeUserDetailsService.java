package com.jaggu.spring.boot.auth.config.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaggu.spring.boot.auth.model.ZerocodeUser;

import java.util.Arrays;

@Service("userService")
public class ZerocodeUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(ZerocodeUserDetailsService.class);

	public ZerocodeUserDetailsService() {}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("getting user details for user [{}]", username);
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
