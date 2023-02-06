package com.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {

		UserDetails test = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
		UserDetails demo = User.withUsername("user").password(encoder.encode("user")).roles("USER").build();

		return new InMemoryUserDetailsManager(test, demo);
	}

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security.csrf().disable().
				authorizeHttpRequests().requestMatchers("/home/welcome").permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/home/admin").hasAnyRole("ADMIN")
				.and()
				.authorizeHttpRequests().requestMatchers("/home/user").hasAnyRole("USER")
				.and()
				.formLogin()
				.and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
