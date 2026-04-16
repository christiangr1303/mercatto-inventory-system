package com.mercatto.dev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserFormDTO {
	
	@NotBlank(message="El email es obligatorio")
	@Email(message="Formato de email invalido")
	private String email;
	
	@NotBlank(message="La contraseña es obligatoria")
	@Size(min=6, message="La contraseña debe tener al menos 6 caracteres")
	private String password;
	
	@NotBlank(message="Debes confirmar la contraseña")
	private String confirmPassword;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
