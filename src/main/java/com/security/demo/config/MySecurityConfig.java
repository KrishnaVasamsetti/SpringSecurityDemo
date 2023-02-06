package com.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.demo.util.JwtRequestFilter;

//import com.security.demo.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {
	

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {

		// Here no need to prefix ROLE_ to the ADMIN role. it will be added automatically. while using in the controller need to use @PreAuthorize("hasAuthority('ROLE_ADMIN')")
		UserDetails test = User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();
		UserDetails demo = User.withUsername("user").password(encoder.encode("user")).roles("USER").build();

		return new InMemoryUserDetailsManager(test, demo);
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//
//		return new UserInfoUserDetailsService();
//	}

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity security, JwtRequestFilter jwtRequestFilter) throws Exception {
		security.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return security.csrf().disable().
				authorizeHttpRequests().requestMatchers("/welcome", "/addUser", "/authenticate").permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers("/**").authenticated()
//				.authorizeHttpRequests().anyRequest().authenticated()
				.and()
				.formLogin()
				.and().build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
//		authenticationProvider.setPasswordEncoder(passwordEncoder());
//		return authenticationProvider;
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
	         throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}

}
