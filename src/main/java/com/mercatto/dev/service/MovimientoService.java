package com.mercatto.dev.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.model.MovimientoInventario;
import com.mercatto.dev.model.Producto;
import com.mercatto.dev.model.TipoMovimiento;
import com.mercatto.dev.repository.MovimientoRepository;
import com.mercatto.dev.repository.ProductoRepository;

@Service
public class MovimientoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	public void registrarMovimiento(Long productoId, String tipo, int cantidad, String motivo) {
		
		Producto producto = productoRepository.findById(productoId)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		
		if (tipo.equals("ENTRADA")) {
			
			producto.setStock(producto.getStock() + cantidad);
			
		} else if (tipo.equals("SALIDA")) {
			
			if (producto.getStock() < cantidad) {
				throw new RuntimeException("Stock insuficiente");
			}
			producto.setStock(producto.getStock() - cantidad);
		}
		
		MovimientoInventario movimiento = new MovimientoInventario();
		movimiento.setProducto(producto);
		movimiento.setCantidad(cantidad);
		movimiento.setTipo(TipoMovimiento.valueOf(tipo));
		movimiento.setFecha(LocalDateTime.now());
		movimiento.setMotivo(motivo);
		
		movimientoRepository.save(movimiento);
		productoRepository.save(producto);
	}
	
}
