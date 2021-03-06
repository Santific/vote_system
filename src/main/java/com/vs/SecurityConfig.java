package com.vs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("admin").password("admin123").roles("ADMIN");
	    auth.inMemoryAuthentication().withUser("petya").password("123456").roles("USER");
	    auth.inMemoryAuthentication().withUser("vasya").password("123456").roles("USER");
	    auth.inMemoryAuthentication().withUser("kolya").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

  	    http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			.and().formLogin().and().logout().logoutUrl("/logout");
		
	}
}
