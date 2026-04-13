package com.mercatto.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	List<Producto> findByCategoriaId(Long categoriaId);
	List<Producto> findByUsuarioId(Long usuarioId);
}
