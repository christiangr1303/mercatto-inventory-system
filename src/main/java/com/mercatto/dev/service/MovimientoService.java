package com.mercatto.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.dto.MovimientoDTO;
import com.mercatto.dev.dto.MovimientoFormDTO;
import com.mercatto.dev.model.MovimientoInventario;
import com.mercatto.dev.model.Producto;
import com.mercatto.dev.model.TipoMovimiento;
import com.mercatto.dev.repository.MovimientoRepository;
import com.mercatto.dev.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimientoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	public List<MovimientoDTO> listar() {
		return movimientoRepository.findAll().stream().map(this::toDTO).toList();
	}
	
	public List<MovimientoDTO> listarConFiltros(
			TipoMovimiento tipo,
			LocalDateTime desde, 
			LocalDateTime hasta) {
		return movimientoRepository.buscarConFiltros(tipo, desde, hasta)
				.stream().map(this::toDTO).toList();
	}
	
	@Transactional
	public void registrarMovimiento(MovimientoFormDTO dto) {
		
		Producto producto = productoRepository.findById(dto.getProductoId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		
		int cantidad = dto.getCantidad();
		
		if (dto.getTipo() == TipoMovimiento.SALIDA) {
			if (producto.getStock() < cantidad) {
				throw new RuntimeException("Stock insuficiente");
			}
			producto.setStock(producto.getStock() - cantidad);
		} else if (dto.getTipo() == TipoMovimiento.ENTRADA) {
			producto.setStock(producto.getStock() + cantidad);
		}
		
		MovimientoInventario movimiento = toEntity(dto);
		
		movimientoRepository.save(movimiento);
		productoRepository.save(producto);
	}
	
	
	
	// MAPPERS
	private MovimientoInventario toEntity(MovimientoFormDTO dto) {
		
		MovimientoInventario m = new MovimientoInventario();
		Producto producto = productoRepository.findById(dto.getProductoId())
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		
		m.setTipo(dto.getTipo());
		m.setCantidad(dto.getCantidad());
		m.setProducto(producto);
		m.setMotivo(dto.getMotivo());
		m.setFecha(LocalDateTime.now());
		return m;
	}
	
	private MovimientoDTO toDTO(MovimientoInventario m) {
		
		MovimientoDTO dto = new MovimientoDTO();
		
		dto.setId(m.getId());
		dto.setTipo(m.getTipo());
		dto.setCantidad(m.getCantidad());
		dto.setFecha(m.getFecha());
		dto.setMotivo(m.getMotivo());
		dto.setProductoNombre(m.getProducto().getNombre());
		return dto;
	}
	
}
