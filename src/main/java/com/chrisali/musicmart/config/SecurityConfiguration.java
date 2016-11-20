package com.chrisali.musicmart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.authoritiesByUsernameQuery("SELECT username, authority FROM users WHERE BINARY username = ?")
				.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE BINARY username = ?")
				.passwordEncoder(passwordEncoder())
				.dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/static/**", "/index").permitAll()
				.and()
			.formLogin().loginPage("/login").failureUrl("/login?error")
				.and()
			.logout().logoutSuccessUrl("/login?loggedout")
				.and()
			.exceptionHandling().accessDeniedPage("/denied")
				.and()
			.csrf();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
