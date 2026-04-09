package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.dto.CategoriaDTO;
import com.mercatto.dev.model.Categoria;
import com.mercatto.dev.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<CategoriaDTO> listar() {
		return categoriaRepository.findAll()
				.stream().map(this::toDTO).toList();
	}
	
	public CategoriaDTO obtenerPorId(Long id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow();
		return toDTO(categoria);
	}
	
	public CategoriaDTO guardar(CategoriaDTO dto) {
		Categoria categoria = toEntity(dto);
		categoriaRepository.save(categoria);
		return toDTO(categoria);
	}
	
	
	// MAPPERS
	private CategoriaDTO toDTO(Categoria c) {
		
		CategoriaDTO dto = new CategoriaDTO();
		
		dto.setId(c.getId());
		dto.setNombre(c.getNombre());
		dto.setDescripcion(c.getDescripcion());
		
		return dto;
		
	}
	
	private Categoria toEntity(CategoriaDTO dto) {
		
		Categoria c = new Categoria();
		
		c.setId(dto.getId());
		c.setNombre(dto.getNombre());
		c.setDescripcion(dto.getDescripcion());
		
		return c;
	}
	
}
