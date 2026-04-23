package com.mercatto.dev.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.model.MovimientoInventario;
import com.mercatto.dev.model.TipoMovimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoInventario, Long>{
	
	List<MovimientoInventario> findByProductoId(Long productoId);
	
	@Query("""
	SELECT m FROM MovimientoInventario m
	WHERE (:tipo IS NULL OR m.tipo = :tipo)
	AND (:desde IS NULL OR m.fecha >= :desde)
	AND (:hasta IS NULL OR m.fecha <= :hasta)
	ORDER BY m.fecha DESC
			""")
	List<MovimientoInventario> buscarConFiltros(
			@Param("tipo") TipoMovimiento tipo,
			@Param("desde") LocalDateTime desde,
			@Param("hasta") LocalDateTime hasta);
}
