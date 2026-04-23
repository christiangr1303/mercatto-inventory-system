package com.mercatto.dev.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mercatto.dev.dto.MovimientoDTO;
import com.mercatto.dev.model.TipoMovimiento;
import com.mercatto.dev.service.MovimientoService;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movService;
	
	@GetMapping
	public String listar(Model model) {
		List<MovimientoDTO> movimientos = movService.listar();
		model.addAttribute("movimientos", movimientos);
		return "movimientos/lista";
	}
	
	@GetMapping("/conFiltros")
	public String listarConFiltros(
			@RequestParam(required=false) TipoMovimiento tipo,
			@RequestParam(required=false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate desde,
			@RequestParam(required=false)
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate hasta,
			Model model) {
		
		LocalDateTime desdeDT = (desde != null) ? desde.atStartOfDay() : null;
		LocalDateTime hastaDT = (hasta != null) ? hasta.atTime(23, 59, 59) : null;
		
		List<MovimientoDTO> movimientos = movService.listarConFiltros(tipo, desdeDT, hastaDT);
		
		model.addAttribute("movimientos", movimientos);
		return "movimientos/lista";
	}
	
}
