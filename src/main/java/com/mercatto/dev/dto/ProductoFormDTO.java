package com.mercatto.dev.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductoFormDTO {
	
	@NotBlank
	private String nombre;
	
	private String descripcion;
	
	@NotNull
	private BigDecimal precio;
	
	@Min(0)
	private int stock;
	
	@NotNull
	private Long categoriaId;
	
	@NotNull
	private Long proveedorId;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}

}
