package com.mercatto.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/home")
	public String home(Model model) {
		return "test/home";
	}
	
	@GetMapping
	public String about(Model model) {
		return "test/about";
	}
	
}
