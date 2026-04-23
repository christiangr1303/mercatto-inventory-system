package com.mercatto.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.mercatto.dev.dto.MovimientoDTO;
import com.mercatto.dev.dto.MovimientoFormDTO;
import com.mercatto.dev.dto.ProductoDTO;
import com.mercatto.dev.service.MovimientoService;
import com.mercatto.dev.service.ProductoService;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoRestController {
	
	@Autowired
	private MovimientoService movService;
	
	@Autowired
	private ProductoService prodService;
	
	@GetMapping("/{id}")
	public ProductoDTO consultarStock(@PathVariable Long id) {
		return prodService.obtenerPorId(id);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registar(@RequestBody MovimientoFormDTO dto) {
		movService.registrarMovimiento(dto);
		return ResponseEntity.ok("Movimiento registrado");
	}
	
}
