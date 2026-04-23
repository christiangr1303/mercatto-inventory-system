package com.mercatto.dev.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
	List<Producto> findByCategoriaId(Long categoriaId);
	
	List<Producto> findByUsuarioId(Long usuarioId);
	
	List<Producto> findByStockBetween(Integer min, Integer max);
	
	@Query("SELECT SUM(p.precio * p.stock) FROM Producto p")
	BigDecimal calcularValorTotalInventario();
	
	int countByStock(int stock);
	
	int countByStockLessThanEqual(int limite);
	
	Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
	Page<Producto> findByCategoriaId(Long categoriaId, Pageable pageable);
	Page<Producto> findByStockBetween(Integer min, Integer max, Pageable pageable);

}
