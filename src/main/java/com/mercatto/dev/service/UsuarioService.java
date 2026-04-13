package com.mercatto.dev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.model.Usuario;
import com.mercatto.dev.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario guardar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
}
