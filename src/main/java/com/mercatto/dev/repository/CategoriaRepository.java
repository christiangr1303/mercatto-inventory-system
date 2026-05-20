package com.mercatto.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercatto.dev.domain.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
