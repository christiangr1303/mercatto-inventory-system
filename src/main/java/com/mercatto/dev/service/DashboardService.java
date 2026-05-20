package com.mercatto.dev.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.repository.MovimientoRepository;
import com.mercatto.dev.repository.ProductoRepository;

@Service
public class DashboardService {
	
	@Autowired
	private ProductoRepository prodRepo;
	
	@Autowired
	private MovimientoRepository movRepo;
	
	// Metodos para KPIS del dashboard	
	public int contarProductos() {
		return prodRepo.contarTotalProductos();
	}	
	public BigDecimal calcularValorTotalInventario() {
		return prodRepo.calcularValorTotalInventario();
	}	
	public int contarProductosSinStock() {
		return prodRepo.countByStock(0);
	}
	public int contarProductosBajoStock() {
		return prodRepo.countByStockLessThanEqual(10);
	}
	
	// Metodos para graficos del dashboard
	public Map<String, List<?>> obtenerDatosGrafico() {
		
		List<Object[]> resultados = movRepo.obtenerValorizadoMensual();
		
		Map<Integer, Integer> movimientosPorMes = new HashMap<>();
		
		for (Object[] fila : resultados) {
			Integer mes = ((Number) fila[0]).intValue();
			Integer total = fila[1] != null ? ((Number) fila[1]).intValue() : 0;
			
			movimientosPorMes.put(mes, total);
		}
		
		List<String> etiquetasMeses = Arrays.asList(
				"", "Ene", "Feb", "Mar", "Abr", "May", "Jun",
				"Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
		);
		
		List<Integer> datosStock = new ArrayList<>();
		
		int acumulado = 0;
		
		for (int mes=1; mes<=12; mes++) {
			int movimientoMes = movimientosPorMes.getOrDefault(mes, 0);
			acumulado += movimientoMes;
			datosStock.add(acumulado);
		}
		
		Map<String, List<?>> datos = new HashMap<>();
		datos.put("datosStock", datosStock);
		datos.put("etiquetasMeses", etiquetasMeses);
		
		return datos;
	}
	
	public Map<String, List<?>> obtenerDatosCategorias() {
		
		List<Object[]> resultados = prodRepo.obtenerProductosPorCategoria();
		
		List<String> categorias = new ArrayList<>();
		List<Long> cantidades = new ArrayList<>();
		
		for (Object[] fila : resultados) {
			categorias.add((String) fila[0]);
			cantidades.add((Long) fila[1]);
		}
		
		Map<String, List<?>> datos = new HashMap<>();
		datos.put("categorias", categorias);
		datos.put("cantidades", cantidades);
		
		return datos;
	}
	
}
