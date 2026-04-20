package com.mercatto.dev.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
	List<Producto> findByCategoriaId(Long categoriaId);
	
	List<Producto> findByUsuarioId(Long usuarioId);
	
	@Query("SELECT SUM(p.precio * p.stock) FROM Producto p")
	BigDecimal calcularValorTotalInventario();
	
	int countByStock(int stock);
	
	int countByStockLessThanEqual(int limite);

}
