package com.satish_usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/index")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/forgotPasswordPage")
	public String forgotPasswordPage() {
		return "forgot_password";
	}

}
