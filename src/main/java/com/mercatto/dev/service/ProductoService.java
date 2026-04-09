package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.dto.ProductoDTO;
import com.mercatto.dev.dto.ProductoFormDTO;
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

	
	public List<ProductoDTO> listar() {
		return productoRepository.findAll()
				.stream().map(this::toDTO).toList();
	}

	public ProductoDTO obtenerPorId(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow();
		return toDTO(producto);
	}

	public Producto guardar(ProductoFormDTO dto) {
		Producto producto = toEntity(dto);
		return productoRepository.save(producto);
	}

	public void desactivar(Long id) {
		Producto producto = productoRepository.findById(id).orElse(null);
		producto.setEstado(false);
		productoRepository.save(producto);
	}
	
	
	// Entity -> DTO
	private ProductoDTO toDTO(Producto p) {
		
		ProductoDTO dto = new ProductoDTO();
		
		dto.setId(p.getId());
		dto.setNombre(p.getNombre());
		dto.setDescripcion(p.getDescripcion());
		dto.setPrecio(p.getPrecio());
		dto.setStock(p.getStock());
		dto.setEstado(p.getEstado());
		
		if (p.getCategoria() != null) {
			dto.setCategoriaId(p.getCategoria().getId());
			dto.setCategoriaNombre(p.getCategoria().getNombre());
		}
		
		if (p.getProveedor() != null) {
			dto.setProveedorId(p.getProveedor().getId());
			dto.setProveedorNombre(p.getProveedor().getNombre());
		}
		
		return dto;
	}
	
	// DTO -> Entity
	private Producto toEntity(ProductoFormDTO dto) {
		
		Producto p = new Producto();
		
		p.setNombre(dto.getNombre());
		p.setDescripcion(dto.getDescripcion());
		p.setPrecio(p.getPrecio());
		p.setStock(dto.getStock());
		
		if (dto.getCategoriaId() != null) {
			Categoria categoria = 
					categoriaRepository.findById(dto.getCategoriaId()).orElseThrow();
			p.setCategoria(categoria);	
		}
		
		if (dto.getProveedorId() != null) {
			Proveedor proveedor = 
					proveedorRepository.findById(dto.getProveedorId()).orElseThrow();
			p.setProveedor(proveedor);
		}
		
		return p;
	}
	
}
