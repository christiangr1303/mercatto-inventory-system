package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.dto.ProveedorDTO;
import com.mercatto.dev.model.Proveedor;
import com.mercatto.dev.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	public List<ProveedorDTO> listar() {
		return proveedorRepository.findAll()
				.stream().map(this::toDTO).toList();
	}
	
	public ProveedorDTO obtenerPorId(Long id) {
		Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
		return toDTO(proveedor);
	}
	
	public ProveedorDTO guardar(ProveedorDTO dto) {
		Proveedor proveedor= toEntity(dto);
		proveedorRepository.save(proveedor);
		return toDTO(proveedor);
	}
	
	private ProveedorDTO toDTO(Proveedor p) {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setId(p.getId());
		dto.setNombre(p.getNombre());
		dto.setEmail(p.getEmail());
		dto.setTelefono(p.getTelefono());
		return dto;
	}
	
	private Proveedor toEntity(ProveedorDTO dto) {
		Proveedor p = new Proveedor();
		p.setId(dto.getId());
		p.setNombre(dto.getNombre());
		p.setEmail(dto.getEmail());
		p.setTelefono(dto.getTelefono());
		return p;
	}
	
}
