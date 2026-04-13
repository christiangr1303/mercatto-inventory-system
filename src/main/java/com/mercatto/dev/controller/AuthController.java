package com.mercatto.dev.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.mercatto.dev.service.UsuarioService;


@Controller
@RequestMapping("/auth")
public class AuthController {
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}
	
	/*
	@PostMapping("/login")
	public String login(@RequestParam String email,
						@RequestParam String password,
						HttpSession session,
						Model model) {
		
		Optional<Usuario> user = usuarioService.buscarPorEmail(email);
		
		if (user.isPresent() && user.get().getPassword().equals(password)) {
			session.setAttribute("usuarioLogueado", user.get());
			return "redirect:/productos";
		}
		
		model.addAttribute("error", "Credenciales incorrectas");
		return "login";
	}
	*/
	
	/*
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
		
		Optional<Usuario> user = usuarioService.buscarPorEmail(usuario.getEmail());
		
		if (user.isPresent() && user.get().getPassword().equals(usuario.getPassword())) {
			return ResponseEntity.ok(user.get());
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
	}
	*/
}
