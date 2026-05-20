package com.mercatto.dev.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercatto.dev.domain.entity.Producto;
import com.mercatto.dev.dto.request.ProductoRequestDTO;
import com.mercatto.dev.dto.response.ProductoResponseDTO;

@Component
public class ProductoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Producto toEntity(ProductoRequestDTO productoRequestDTO) {
		return modelMapper.map(productoRequestDTO, Producto.class);
	}
	
	public ProductoResponseDTO toResponseDTO(Producto producto) {
		return modelMapper.map(producto, ProductoResponseDTO.class);
	}
	
	public List<ProductoResponseDTO> toResponseDTOList(List<Producto> productos) {
		return productos.stream().map(this::toResponseDTO).toList();
	}
}
