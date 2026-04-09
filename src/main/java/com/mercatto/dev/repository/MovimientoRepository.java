package com.mercatto.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.model.MovimientoInventario;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoInventario, Long>{
	
	List<MovimientoInventario> findByProductoId(Long productoId);
}
