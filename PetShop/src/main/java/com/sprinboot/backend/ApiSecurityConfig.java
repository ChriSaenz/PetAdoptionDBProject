package com.sprinboot.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	MyUserDetailService myUserDetailService = new MyUserDetailService();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().permitAll()
			//	.antMatchers(HttpMethod.GET, "/pet").authenticated()
	//			.antMatchers(HttpMethod.POST, "/pet").hasAnyAuthority("EMPLOYEE")
		//		.antMatchers(HttpMethod.PUT, "/pet").hasAnyAuthority("EMPLOYEE")
	//			.antMatchers(HttpMethod.DELETE, "/pet").hasAnyAuthority("EMPLOYEE")
	//			.antMatchers("/request").hasAnyAuthority("EMPLOYEE")
		//		.antMatchers("/receipt").hasAnyAuthority("EMPLOYEE")
			//	.antMatchers("/employee").hasAnyAuthority("ADMIN")
				.and().httpBasic()
				.and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		  auth.inMemoryAuthentication()
		
				.withUser("bob")
				.password(getPasswordEncoder().encode("bob"))
				.roles("ADMIN");
			
		//auth.authenticationProvider(getCustomProvider());
	}

	//@Override
	//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(getCustomProvider());
	//}

	@SuppressWarnings("unused")
	private DaoAuthenticationProvider getCustomProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(myUserDetailService);
		return dao;
	}

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
