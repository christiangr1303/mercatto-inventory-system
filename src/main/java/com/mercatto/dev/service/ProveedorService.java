package com.mercatto.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercatto.dev.model.Proveedor;
import com.mercatto.dev.repository.ProveedorRepository;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	public List<Proveedor> listar() {
		return proveedorRepository.findAll();
	}
	
	public Proveedor obtenerPorId(Long id) {
		return proveedorRepository.findById(id).orElse(null);
	}
	
}
