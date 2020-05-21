package com.group158.UrbanAdventure.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	

	// Trying Form-Based Authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/all").permitAll()
				.antMatchers("/api/signin").permitAll()
				.antMatchers("/api/search/**").permitAll()
				.antMatchers("/api/get//**").permitAll()
				.anyRequest().authenticated();


		/*http
				.authorizeRequests()
				.antMatchers("/**")
				.hasRole("USER")
				.and()
				.formLogin();*/


	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
                .and().withUser("admin").password("{noop}password").roles("ADMIN", "USER");
	}
 }