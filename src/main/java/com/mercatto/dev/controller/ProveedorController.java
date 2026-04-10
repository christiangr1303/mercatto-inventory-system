package com.mercatto.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.dto.ProveedorDTO;
import com.mercatto.dev.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("proveedores", proveedorService.listar());
		return "proveedores/lista";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("proveedor", new ProveedorDTO());
		return "proveedores/form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute ProveedorDTO dto) {
		proveedorService.guardar(dto);
		return "redirect:/proveedores";
	}
	
}
