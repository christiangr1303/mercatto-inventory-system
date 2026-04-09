package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.model.Categoria;
import com.mercatto.dev.model.Producto;
import com.mercatto.dev.model.Proveedor;
import com.mercatto.dev.repository.CategoriaRepository;
import com.mercatto.dev.repository.ProductoRepository;
import com.mercatto.dev.repository.ProveedorRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	
	public List<Producto> listar() {
		return productoRepository.findAll();
	}
	
	public Producto obtenerPorId(Long id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public Producto guardar(Producto producto, Long categoriaId, Long proveedorId) {
		
		if (categoriaId != null) {
			Categoria categoria = categoriaRepository.findById(categoriaId).orElse(null);
			producto.setCategoria(categoria);
		}
		
		if (proveedorId != null) {
			Proveedor proveedor = proveedorRepository.findById(proveedorId).orElse(null);
			producto.setProveedor(proveedor);
		}
		
		return productoRepository.save(producto);
	}
	
	public void desactivar(Long id) {
		Producto producto = obtenerPorId(id);
		producto.setEstado(false);
		productoRepository.save(producto);
	}
	
}
