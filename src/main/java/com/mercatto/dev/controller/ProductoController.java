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
import org.springframework.web.bind.annotation.RequestParam;

import com.mercatto.dev.dto.ProductoDTO;
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
	public String listar(
			@RequestParam(required=false) String q,
			@RequestParam(required=false) Long categoria_id,
			@RequestParam(required=false) String rangoStock,
			Model model) {
		
		model.addAttribute("categorias", categoriaService.listar());
		
		List<ProductoDTO> productos = null;
		if (q != null && !q.isBlank()) {
			productos = productoService.buscarPorTextoIngresado(q);
		} else if (categoria_id != null) {
			productos = productoService.listarPorCategoria(categoria_id);
		} else if (rangoStock != null && !rangoStock.isBlank()) {
			String[] partes = rangoStock.split("-");
			Integer min = Integer.parseInt(partes[0]);
			Integer max = Integer.parseInt(partes[1]);
			
			productos = productoService.listarProductosConStockEntre(min, max);
		} else {
			productos = productoService.listar();
		}
		
		model.addAttribute("productos", productos);
		return "productos/lista";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new ProductoFormDTO());
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("proveedores", proveedorService.listar());
		return "productos/form";
	}
	
	@GetMapping("editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("producto", productoService.obtenerPorId(id));
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("proveedores", proveedorService.listar());
		return "productos/form";
	}
	
	@PostMapping("/guardar")
	public String guardarProducto(@ModelAttribute ProductoFormDTO dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails userDetails = (UsuarioDetails) auth.getPrincipal();
		
		Usuario usuario = userDetails.getUsuario();
		
		productoService.crearProducto(dto, usuario);
		return "redirect:/productos";
	}
	
	@GetMapping("/cambiarEstado/{id}")
	public String cambiarEstado(@PathVariable Long id) {
		productoService.cambiarEstado(id);
		return "redirect:/productos";
	}
	
	// Agregados recientemente
	public String listarProductos(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
		List<Producto> productos = productoService.listarPorUsuario(usuario.getId());
		model.addAttribute("productos", productos);
		return "productos/lista";
	}

}
