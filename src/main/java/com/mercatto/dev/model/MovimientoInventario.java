package com.mercatto.dev.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MovimientoInventario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private TipoMovimiento tipo;
	
	private int cantidad;
	
	private LocalDateTime fecha;
	
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;
	
	
	public MovimientoInventario() {
		super();
	}

	public MovimientoInventario(TipoMovimiento tipo, int cantidad, LocalDateTime fecha, String motivo,
			Producto producto) {
		super();
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.motivo = motivo;
		this.producto = producto;
	}

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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
