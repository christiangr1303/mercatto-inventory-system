package com.mercatto.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.dto.ProductoFormDTO;
import com.mercatto.dev.model.Producto;
import com.mercatto.dev.model.Usuario;
import com.mercatto.dev.security.UsuarioDetails;
import com.mercatto.dev.service.CategoriaService;
import com.mercatto.dev.service.ProductoService;
import com.mercatto.dev.service.ProveedorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("productos", productoService.listar());
		return "productos/lista";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new ProductoFormDTO());
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("proveedores", proveedorService.listar());
		return "productos/form";
	}
	
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("producto", productoService.obtenerPorId(id));
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("proveedores", proveedorService.listar());
		return "productos/form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute ProductoFormDTO dto) {
		productoService.guardar(dto);
		return "redirect:/productos";
	}
	
	@PostMapping("/cambiarEstado")
	public String cambiarEstado(Long id) {
		productoService.cambiarEstado(id);
		return "redirect:/productos";
	}
	
	// Agregados recientemente
	
	@PostMapping("/guardarv2")
	public String guardarProducto(@ModelAttribute ProductoFormDTO dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails userDetails = (UsuarioDetails) auth.getPrincipal();
		
		Usuario usuario = userDetails.getUsuario();
		
		productoService.crearProducto(dto, usuario);
		return "redirect:/productos";
	}
	
	public String listarProductos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		List<Producto> productos = productoService.listarPorUsuario(usuario.getId());
		model.addAttribute("productos", productos);
		return "productos/lista";
	}

}
