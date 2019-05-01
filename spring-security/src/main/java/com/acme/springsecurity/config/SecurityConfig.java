package com.acme.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity //switch-off out of the box security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(GET, "/hello").permitAll()
		.antMatchers(GET, "/admin/users").permitAll() //temporary
		.anyRequest().authenticated().and().httpBasic();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Autowired
	public void configureGrobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder
//				.inMemoryAuthentication()
//				.withUser("user")
//				.password("{noop}password") //withDefaultPasswordEncoder
//				.roles("USER");

		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
}
