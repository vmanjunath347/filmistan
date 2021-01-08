package com.filmistan.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET,"/filmistan").permitAll()
		.antMatchers(HttpMethod.POST, "filmistan/register").permitAll()
		.antMatchers("/filmistan/admin/").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails willSmith = User.builder().username("willsmith")
				.password(passwordEncoder.encode("adminpassword"))
				.roles("ADMIN").build();
				
		return new InMemoryUserDetailsManager(willSmith);
	}

}
