package com.apigateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerDemo {
	
	@GetMapping("/")
	public String page() {
		System.out.println("========home========");
		return "home.html";
	}
	
	@GetMapping("/home")
	public String welcomepage() {
		System.out.println("========home========");
		return "home";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET )
	public String login() {
		System.out.println("========login========");
		return "login";
	}
	
	@GetMapping("/logout-success")
	public String logout() {
		System.out.println("=======logout=========");
		return "logout";
	}
	
}
