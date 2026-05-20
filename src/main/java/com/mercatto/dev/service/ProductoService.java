package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mercatto.dev.domain.entity.Categoria;
import com.mercatto.dev.domain.entity.Producto;
import com.mercatto.dev.domain.entity.Proveedor;
import com.mercatto.dev.domain.entity.Usuario;
import com.mercatto.dev.dto.request.ProductoRequestDTO;
import com.mercatto.dev.dto.response.ProductoResponseDTO;
import com.mercatto.dev.mappers.ProductoMapper;
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
	
	@Autowired
	private ProductoMapper productoMapper;

	
	// Metodos CRUD
	public List<ProductoResponseDTO> listar() {
		List<Producto> productos = productoRepository.findAll();
		return productoMapper.toResponseDTOList(productos);
	}
	public ProductoResponseDTO obtenerPorId(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow();
		return productoMapper.toResponseDTO(producto);
	}
	public ProductoResponseDTO crearProducto (ProductoRequestDTO dto, Usuario usuario) {
		Producto producto = productoMapper.toEntity(dto);
		producto.setUsuario(usuario);
		productoRepository.save(producto);
		return productoMapper.toResponseDTO(producto);
	}
	public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
		Producto p = productoRepository.findById(id).orElseThrow();
		
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
		
		Proveedor proveedor = proveedorRepository.findById(dto.getProveedorId())
				.orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
		
		p.setNombre(dto.getNombre());
		p.setDescripcion(dto.getDescripcion());
		p.setPrecio(dto.getPrecio());
		p.setCategoria(categoria);
		p.setProveedor(proveedor);
		
		return productoMapper.toResponseDTO(productoRepository.save(p));
	}
	public void cambiarEstado(Long id) {
		Producto producto = productoRepository.findById(id).orElseThrow();
		producto.setEstado(!producto.getEstado());
		productoRepository.save(producto);
	}
	
	
	// Metodos que LISTAN segun CONDICIONES
	public List<ProductoResponseDTO> listarPorCategoria(Long categoriaId) {
		List<Producto> productos = productoRepository.findByCategoriaId(categoriaId);
		return productoMapper.toResponseDTOList(productos);
	}	
	public List<ProductoResponseDTO> buscarPorTextoIngresado(String nombre) {
		List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
		return productoMapper.toResponseDTOList(productos);
	}
	public List<ProductoResponseDTO> listarProductosConStockEntre(Integer min, Integer max) {
		if (min != null && max != null) {
			List<Producto> productos = productoRepository.findByStockBetween(min, max);
			return productoMapper.toResponseDTOList(productos);
		} else {
			return listar();
		}
	}
	public List<ProductoResponseDTO> listarPorUsuario(Long usuarioId) {
		List<Producto> productos = productoRepository.findByUsuarioId(usuarioId);
		return productoMapper.toResponseDTOList(productos);
	}
	
	
	// Metodos para PAGINACION (uso del objeto Page)
	public Page<ProductoResponseDTO> listar(Pageable pageable) {
		Page<Producto> productos = productoRepository.findAll(pageable);
		return productos.map(productoMapper::toResponseDTO);
	}
	public Page<ProductoResponseDTO> listarPorCategoria(Long categoriaId, Pageable pageable) {
		Page<Producto> productos = productoRepository.findByCategoriaId(categoriaId, pageable);
		return productos.map(productoMapper::toResponseDTO);
	}
	public Page<ProductoResponseDTO> listarProductosConStockEntre(Integer min, Integer max, Pageable pageable) {
		if (min !=null && max != null) {
			Page<Producto> productos = productoRepository.findByStockBetween(min, max, pageable);
			return productos.map(productoMapper::toResponseDTO);
		} else {
			return listar(pageable);
		}		
	}
	public Page<ProductoResponseDTO> buscarPorTextoIngresado(String nombre, Pageable pageable) {
		Page<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);
		return productos.map(productoMapper::toResponseDTO);
	}	

	

	
}
