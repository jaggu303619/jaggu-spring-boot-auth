package com.jaggu.spring.boot.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class JagguDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	private SessionHistoryService zcLogSessionHistoryService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = null;
		log.info("authenticate user [{}] with Zerocode", name);
		try {
			userDetails = getUserDetailsService().loadUserByUsername(name);
		} catch (UsernameNotFoundException ex) {
			log.error("User [{}] not found", name);
		} catch (Exception e) {
			log.error("Exception [{}] in CustomDaoAuthenticationProvider for user [{}] ", e, name);
		}

		if (userDetails != null) {
			if (!userDetails.isEnabled()) {
				log.error("User not active for login [{}]", name);
				throw new BadCredentialsException("User not active");
			}
			if (getPasswordEncoder().matches(password, userDetails.getPassword())) {
				zcLogSessionHistoryService.logSessionHistory(name);
				return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
			}
		}
		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


}
