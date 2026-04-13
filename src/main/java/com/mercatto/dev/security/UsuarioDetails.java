package com.mercatto.dev.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mercatto.dev.model.Usuario;

public class UsuarioDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UsuarioDetails(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public @Nullable String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}
	
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
	
	public Usuario getUsuario() {
		return usuario;
	}
	
}
