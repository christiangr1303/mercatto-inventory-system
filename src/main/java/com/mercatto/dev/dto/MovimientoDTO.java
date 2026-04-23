package com.mercatto.dev.dto;

import java.time.LocalDateTime;

import com.mercatto.dev.model.TipoMovimiento;

public class MovimientoDTO {
	
	private Long id;
	private TipoMovimiento tipo;	
	private int cantidad;
	private LocalDateTime fecha;
	private String motivo;
	private String productoNombre;
	
	// Getters y Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoMovimiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getProductoNombre() {
		return productoNombre;
	}
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	
}
