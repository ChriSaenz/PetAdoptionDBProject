package com.sprinboot.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sprinboot.backend.service.MyUserDetailService;

@SuppressWarnings("deprecation")
@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailService myUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//TODO Figure out how to limit extra instructions (/customer/{id})
		http.authorizeRequests()
				.antMatchers("/pet").authenticated()
				.antMatchers("/customer").authenticated()
				.antMatchers("/request").authenticated()
				.antMatchers("/receipt").authenticated()
				// For multiple roles to have authority: .hasAnyAuthority("ADMIN","USER")
				.antMatchers("/employee").hasAnyAuthority("ADMIN")		
				.anyRequest().permitAll()
				.and().httpBasic()
				.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getCustomProvider());
	}

	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		return passwordEncoder;
	}
	
	private DaoAuthenticationProvider getCustomProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(myUserDetailService);
		return dao;
	}
}
