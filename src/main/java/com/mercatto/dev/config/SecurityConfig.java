package com.mercatto.dev.config;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import com.mercatto.dev.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//@Autowired
	//private UsuarioDetailsService usuarioDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf -> csrf.disable()) // Se usa cuando se hace pruebas
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**", "/css/**").permitAll()
				.requestMatchers("/auth/**", "/error/**").permitAll()
				.requestMatchers("/api/movimientos/**").permitAll()
				.anyRequest().authenticated() // Todo lo demas requiere autenticacion
		)
		.formLogin(form -> form
				.loginPage("/auth/login") // No uses tu login, tengo uno por defecto
				.defaultSuccessUrl("/index", true) // Despues de login exitoso, redirige
				.permitAll()
		)
		.logout(logout -> logout
				.logoutSuccessUrl("/auth/login?logout") // Despues del logout, redirige (con parametro)
				.permitAll()
		)
		.exceptionHandling(e -> e
				.accessDeniedPage("/error/403")
		);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Define como se encriptan contraseñas
		return new BCryptPasswordEncoder();
	}
	
}
