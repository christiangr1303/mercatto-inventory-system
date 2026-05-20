package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mercatto.dev.domain.entity.Proveedor;
import com.mercatto.dev.dto.response.ProveedorResponseDTO;
import com.mercatto.dev.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	public List<ProveedorResponseDTO> listar() {
		return proveedorRepository.findAll()
				.stream().map(this::toDTO).toList();
	}
	
	public Page<ProveedorResponseDTO> listar(Pageable pageable) {
		return proveedorRepository.findAll(pageable).map(this::toDTO);
	}
	
	public ProveedorResponseDTO obtenerPorId(Long id) {
		Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
		return toDTO(proveedor);
	}
	
	public ProveedorResponseDTO guardar(ProveedorResponseDTO dto) {
		Proveedor proveedor= toEntity(dto);
		proveedorRepository.save(proveedor);
		return toDTO(proveedor);
	}
	
	private ProveedorResponseDTO toDTO(Proveedor p) {
		ProveedorResponseDTO dto = new ProveedorResponseDTO();
		dto.setId(p.getId());
		dto.setNombre(p.getNombre());
		dto.setEmail(p.getEmail());
		dto.setTelefono(p.getTelefono());
		return dto;
	}
	
	private Proveedor toEntity(ProveedorResponseDTO dto) {
		Proveedor p = new Proveedor();
		p.setId(dto.getId());
		p.setNombre(dto.getNombre());
		p.setEmail(dto.getEmail());
		p.setTelefono(dto.getTelefono());
		return p;
	}
	
}
