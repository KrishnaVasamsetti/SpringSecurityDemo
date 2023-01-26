package com.security.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.security.demo.entity.UserInfo;
//import com.security.demo.repository.UserRepository;

//@Service
//public class UserInfoUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserInfo> userInfo = userRepository.findByName(username);		
//		UserDetails details = userInfo.map(UserInfoUserDetails::new)
//				.orElseThrow(()-> new UsernameNotFoundException("User not found " + username));
//		return details;
//	}
//
//	public UserInfo addUser(UserInfo userInfo) {
//		return userRepository.save(userInfo);
//	}
//	
//}
