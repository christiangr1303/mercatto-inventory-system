package com.mercatto.dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mercatto.dev.dto.UserFormDTO;
import com.mercatto.dev.model.Usuario;
import com.mercatto.dev.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String guardar(UserFormDTO dto) {
		
		if (!dto.getPassword().equals(dto.getConfirmPassword())) {
			return "Las contraseñas no coinciden";
		}
		
		if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
			return "El correo ya esta registrado";
		}
		
		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		usuarioRepository.save(user);
		
		return "ok";
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
}
