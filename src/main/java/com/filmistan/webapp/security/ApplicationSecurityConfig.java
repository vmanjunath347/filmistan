package com.filmistan.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//@Autowired
	//PasswordEncoder passwordEncoder;
	
	private UserPrincipalDetailsService userPrincipalDetailsService;
	
	
	
	public ApplicationSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/filmistan/register").permitAll()
		.antMatchers(HttpMethod.GET,"/filmistan").permitAll()
		.antMatchers("/filmistan/admin/**").hasRole("ADMIN")
		.antMatchers("/filmistan/user/**").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder  auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		
		return daoAuthenticationProvider;
	}

	
	/*
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails willSmith = User.builder().username("willsmith")
				.password(passwordEncoder.encode("adminpassword"))
				.roles("ADMIN").build();
				
		return new InMemoryUserDetailsManager(willSmith);
	}
	*/
	

}
