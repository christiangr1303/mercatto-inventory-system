package com.mercatto.dev.dto.response;

import java.math.BigDecimal;

public class ProductoResponseDTO {
	

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private int stock;
	private Boolean estado;
	
	private String categoriaNombre;
	private String proveedorNombre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	public String getProveedorNombre() {
		return proveedorNombre;
	}
	public void setProveedorNombre(String proveedorNombre) {
		this.proveedorNombre = proveedorNombre;
	}
	
}
