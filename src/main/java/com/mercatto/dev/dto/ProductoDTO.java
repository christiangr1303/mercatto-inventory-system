package com.mercatto.dev.dto;

import java.math.BigDecimal;

public class ProductoDTO {
	

	private Long id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Boolean estado;
	
	//private Long categoriaId;
	private String categoriaNombre;
	//private Long proveedorId;
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	/*
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	*/
	public String getCategoriaNombre() {
		return categoriaNombre;
	}
	public void setCategoriaNombre(String categoriaNombre) {
		this.categoriaNombre = categoriaNombre;
	}
	/*
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	*/
	public String getProveedorNombre() {
		return proveedorNombre;
	}
	public void setProveedorNombre(String proveedorNombre) {
		this.proveedorNombre = proveedorNombre;
	}
	
}
