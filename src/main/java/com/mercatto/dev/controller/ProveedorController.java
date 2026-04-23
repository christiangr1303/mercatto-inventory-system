package com.mercatto.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercatto.dev.dto.ProveedorDTO;
import com.mercatto.dev.service.ProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	public String listar(
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="10") int size,
			Model model) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<ProveedorDTO> proveedoresPage;
		
		proveedoresPage = proveedorService.listar(pageable);
		
		model.addAttribute("proveedores", proveedoresPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", proveedoresPage.getTotalPages());
		
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
