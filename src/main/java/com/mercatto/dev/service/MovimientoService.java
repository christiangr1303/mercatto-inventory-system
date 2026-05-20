package com.mercatto.dev.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.domain.entity.MovimientoInventario;
import com.mercatto.dev.domain.entity.Producto;
import com.mercatto.dev.domain.enums.TipoMovimiento;
import com.mercatto.dev.dto.request.MovimientoRequestDTO;
import com.mercatto.dev.dto.response.MovimientoResponseDTO;
import com.mercatto.dev.repository.MovimientoRepository;
import com.mercatto.dev.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class MovimientoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	public List<MovimientoResponseDTO> listar() {
		return movimientoRepository.findAll().stream().map(this::toDTO).toList();
	}
	
	public List<MovimientoResponseDTO> listarConFiltros(
			TipoMovimiento tipo,
			LocalDateTime desde, 
			LocalDateTime hasta) {
		return movimientoRepository.buscarConFiltros(tipo, desde, hasta)
				.stream().map(this::toDTO).toList();
	}
	
	@Transactional
	public void registrarMovimiento(MovimientoRequestDTO dto) {
		
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
	private MovimientoInventario toEntity(MovimientoRequestDTO dto) {
		
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
	
	private MovimientoResponseDTO toDTO(MovimientoInventario m) {
		
		MovimientoResponseDTO dto = new MovimientoResponseDTO();
		
		dto.setId(m.getId());
		dto.setTipo(m.getTipo());
		dto.setCantidad(m.getCantidad());
		dto.setFecha(m.getFecha());
		dto.setMotivo(m.getMotivo());
		dto.setProductoNombre(m.getProducto().getNombre());
		return dto;
	}
	
}
