package com.jaggu.spring.boot.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.jaggu.spring.boot.auth.service.JagguDaoAuthenticationProvider;
import com.jaggu.spring.boot.auth.service.JagguUserDetailsService;

@Configuration
public class JagguWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	// https://javadeveloperzone.com/spring-boot/spring-security-oauth-2-0-authentication-server/
	@Autowired
	private JagguPasswordEncoder jagguPasswordEncoder;
	
	@Autowired
	private JagguUserDetailsService jagguUserDetailsService;	

	@Autowired
	public JagguWebSecurityConfigurerAdapter() {
		super();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(jagguDaoAuthenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}

	@Bean
	public JagguDaoAuthenticationProvider jagguDaoAuthenticationProvider() {
		JagguDaoAuthenticationProvider bean = new JagguDaoAuthenticationProvider();
		bean.setUserDetailsService(jagguUserDetailsService);
		bean.setPasswordEncoder(jagguPasswordEncoder);
		return bean;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
