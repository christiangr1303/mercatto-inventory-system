package com.mercatto.dev.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mercatto.dev.domain.entity.Usuario;
import com.mercatto.dev.domain.enums.Role;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final Usuario usuario;

	public CustomUserDetails(Usuario usuario) {
		this.usuario = usuario;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public @Nullable String getPassword() {
		// Devuelve contraseña encriptada
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		// Defines que es el username (username = email)
		return usuario.getEmail();
	}
	
	public String getFirstName() {
		return usuario.getFirstName();
	}
	
	public String getLastName() {
		return usuario.getLastName();
	}
	
	public Role getRole() {
		return usuario.getRole();
	}
	
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isEnabled() { return true; }
	
	public Usuario getUsuario() {
		return usuario;
	}
	
}
