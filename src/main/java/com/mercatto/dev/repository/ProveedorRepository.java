package com.mercatto.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.domain.entity.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}
