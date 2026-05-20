package com.mercatto.dev.dto.request;

import com.mercatto.dev.domain.enums.TipoMovimiento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MovimientoRequestDTO {

	@NotNull
	private TipoMovimiento tipo;
	
	@NotNull
	@Min(1)
	private int cantidad;
	
	@NotNull
	private Long productoId;
	
	@NotNull
	private String motivo;

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
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
