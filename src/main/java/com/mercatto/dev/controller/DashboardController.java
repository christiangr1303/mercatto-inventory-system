package com.mercatto.dev.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.service.ProductoService;

@Controller
@RequestMapping("/index")
public class DashboardController {
	
	@Autowired
	private ProductoService prodService;
	
	
	@GetMapping
	public String mostrarDashboard(Model model) {
		model.addAttribute("totalProductos", prodService.contarProductos());
		model.addAttribute("valorInventario", prodService.calcularValorTotalInventario());
		model.addAttribute("prodBajoStock", prodService.contarProductosBajoStock());
		model.addAttribute("prodSinStock", prodService.contarProductosSinStock());
		
		// Datos para el grafico de area
		List<Integer> datosStock =  Arrays.asList(500, 450, 600, 550, 800, 750);
		List<String> etiquetasMeses = Arrays.asList("Ene", "Feb", "Mar", "Abr", "May", "Jun");
		
		model.addAttribute("datosGrafico", datosStock);
		model.addAttribute("etiquetasGrafico", etiquetasMeses);
		
		// Datos para el grafico de barras
		List<String> categorias = Arrays.asList("Computadoras", "Components", "Perifericos", "Monitores", "Storage", "Networking");
		List<Integer> cantidades = Arrays.asList(25, 40, 15, 10, 30, 20);
		
		model.addAttribute("labelsCategorias", categorias);
		model.addAttribute("datosCategorias", cantidades);
		
		return "index";
	}
	
	
}
