package com.mercatto.dev.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercatto.dev.service.DashboardService;

@Controller
@RequestMapping("/index")
public class DashboardController {
	
	@Autowired
	private DashboardService dashService;
	
	
	@GetMapping
	public String mostrarDashboard(Model model) {
		model.addAttribute("totalProductos", dashService.contarProductos());
		model.addAttribute("valorInventario", dashService.calcularValorTotalInventario());
		model.addAttribute("prodBajoStock", dashService.contarProductosBajoStock());
		model.addAttribute("prodSinStock", dashService.contarProductosSinStock());
		
		// Datos para el grafico de area
		/*
		List<Integer> datosStock =  Arrays.asList(500, 450, 600, 550, 800, 750);
		List<String> etiquetasMeses = Arrays.asList("Ene", "Feb", "Mar", "Abr", "May", "Jun");
		*/
		Map<String, List<?>> graficoArea = dashService.obtenerDatosGrafico();
		
		model.addAttribute("datosStock", graficoArea.get("datosStock"));
		model.addAttribute("etiquetasMeses", graficoArea.get("etiquetasMeses"));
		
		// Datos para el grafico de barras
		/*
		List<String> categorias = Arrays.asList("Computadoras", "Components", "Perifericos", "Monitores", "Storage", "Networking");
		List<Integer> cantidades = Arrays.asList(25, 40, 15, 10, 30, 20);
		*/
		Map<String, List<?>> graficoBarras = dashService.obtenerDatosCategorias();
		model.addAttribute("labelsCategorias", graficoBarras.get("categorias"));
		model.addAttribute("datosCategorias", graficoBarras.get("cantidades"));
		
		return "index";
	}
	
	
}
