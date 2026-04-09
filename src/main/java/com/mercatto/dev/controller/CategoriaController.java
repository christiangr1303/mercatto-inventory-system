package com.mercatto.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.dto.CategoriaDTO;
import com.mercatto.dev.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaService.listar());
		return "categorias/lista";
	}
	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("categoria", new CategoriaDTO());
		return "categorias/form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute CategoriaDTO dto) {
		categoriaService.guardar(dto);
		return "redirect:/categorias";
	}
	
}
