package com.mercatto.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.dto.request.SignUpRequestDTO;
import com.mercatto.dev.service.UsuarioService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "auth/login";
	}
	
	@GetMapping("/register")
	public String mostrarRegistro(Model model) {
		model.addAttribute("usuario", new SignUpRequestDTO());
		return "auth/register";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("usuario") SignUpRequestDTO dto,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "auth/register";
		}
		
		usuarioService.guardar(dto);
		return "redirect:/auth/login";
	}
	
}
