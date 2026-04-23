package com.mercatto.dev.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
		return ResponseEntity.badRequest().body(
				Map.of("error", ex.getMessage(), "status", 400));
	}
	
	public ResponseEntity<?> handleGeneral(Exception ex) {
		return ResponseEntity.status(500).body(
				Map.of("error", "Error interno del servidor", "status", 500));
	}
	
}
