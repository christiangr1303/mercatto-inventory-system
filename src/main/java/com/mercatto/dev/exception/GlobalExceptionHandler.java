package com.mercatto.dev.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public String handleBadRequest(BadRequestException ex, Model model) {
		model.addAttribute("error", ex.getMessage());
		return "register";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleGeneral(Exception ex, Model model) {
		model.addAttribute("error", "Ocurrio un error inesperado");
		return "error";
	}
	
}
