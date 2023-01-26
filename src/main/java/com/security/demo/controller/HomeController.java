package com.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.demo.entity.AuthRequest;
import com.security.demo.util.JwtUtils;

//import com.security.demo.entity.UserInfo;
//import com.security.demo.repository.UserRepository;
//import com.security.demo.service.UserInfoUserDetailsService;

@RestController
@RequestMapping
public class HomeController {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private UserInfoUserDetailsService userService;

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "admin spring security application";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome spring security application";
	}
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/user")
	public String user() {
		return "user spring security application";
	}
	
//	@PostMapping("/addUser")
//	public UserInfo addUser(@RequestBody UserInfo userInfo) {
//		return userService.addUser(userInfo);
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch(Exception exception) {
			return new ResponseEntity<String>("Invalid login details", HttpStatusCode.valueOf(403));
		}
		return ResponseEntity.ok(jwtUtils.generateToken(authRequest.getUsername()));
		
	}
	
}
