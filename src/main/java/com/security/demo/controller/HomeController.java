package com.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/admin")
	public String admin() {
		return "admin spring security application";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome spring security application";
	}
	
	@GetMapping("/user")
	public String all() {
		return "user spring security application";
	}
}
