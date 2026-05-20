package com.mercatto.dev.exception;

import java.time.LocalDateTime;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, Model model) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		model.addAttribute("error", errorDetails);
		return "error/404";
	}
	
	@ExceptionHandler(BadRequestException.class)
	public String handleBadRequestException(BadRequestException ex, WebRequest request, Model model) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		model.addAttribute("error", errorDetails);
		return "error/400";
	}
	
	@ExceptionHandler(Exception.class)
	public String handleGeneral(Exception ex, WebRequest request, Model model) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		model.addAttribute("error", errorDetails);
		return "error/500";
	}
	
}
