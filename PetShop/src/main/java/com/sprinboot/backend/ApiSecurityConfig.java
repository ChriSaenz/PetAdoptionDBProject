package com.sprinboot.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sprinboot.backend.service.MyUserDetailService;

public class ApiSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/pet").authenticated()
//		.antMatchers(HttpMethod.POST, "/pet").hasAnyAuthority("EMPLOYEE")
//		.antMatchers(HttpMethod.PUT, "/pet").hasAnyAuthority("EMPLOYEE")
//		.antMatchers(HttpMethod.DELETE, "/pet").hasAnyAuthority("EMPLOYEE")
//		.antMatchers("/request").hasAnyAuthority("EMPLOYEE")
//		.antMatchers("/receipt").hasAnyAuthority("EMPLOYEE")
//		.antMatchers("/employee").hasAnyAuthority("ADMIN")
		.anyRequest().permitAll()
		.and().httpBasic()
		.and().csrf().disable();
	}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(getCustomProvider());
		}
		
		private DaoAuthenticationProvider getCustomProvider() {
			DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
			dao.setPasswordEncoder(getPasswordEncoder());
			dao.setUserDetailService(myUserDetailService);
			return dao;
		}

	    @Bean
	    PasswordEncoder getPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	}