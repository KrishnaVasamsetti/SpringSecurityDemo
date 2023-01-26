package com.security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

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
}
