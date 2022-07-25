package com.sprinboot.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sprinboot.backend.service.MyUserDetailService;

@SuppressWarnings("deprecation")
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

	MyUserDetailService myUserDetailService = new MyUserDetailService();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/pet").authenticated()
				.antMatchers(HttpMethod.POST, "/pet").hasAnyAuthority("EMPLOYEE").antMatchers(HttpMethod.PUT, "/pet")
				.hasAnyRole("EMPLOYEE").antMatchers(HttpMethod.DELETE, "/pet").hasAnyAuthority("EMPLOYEE")
				.antMatchers("/request").hasAnyAuthority("EMPLOYEE")
				.antMatchers("/receipt").hasAnyAuthority("EMPLOYEE")
				.antMatchers("/employee").hasAnyAuthority("ADMIN")
				.anyRequest().permitAll().and().httpBasic().and()
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("Boss")
		 * .password(getPasswordEncoder().encode("P42")) .roles("EMPLOYEE")
		 * .roles("ADMIN") .and() .withUser("Firestar")
		 * .password(getPasswordEncoder().encode("pretty")) .roles("EMPLOYEE");
		 */
		auth.authenticationProvider(getCustomProvider());
	}

	private AuthenticationProvider getCustomProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(myUserDetailService);
		return dao;
	}

	@Bean
	private PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
