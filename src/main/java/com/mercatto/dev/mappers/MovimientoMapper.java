package com.mercatto.dev.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mercatto.dev.domain.entity.MovimientoInventario;
import com.mercatto.dev.dto.request.MovimientoRequestDTO;
import com.mercatto.dev.dto.response.MovimientoResponseDTO;

@Component
public class MovimientoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public MovimientoInventario toEntity(MovimientoRequestDTO movimientoRequestDTO) {
		return modelMapper.map(movimientoRequestDTO, MovimientoInventario.class);
	}
	
	public MovimientoResponseDTO toResponseDTO(MovimientoInventario movimiento) {
		return modelMapper.map(movimiento, MovimientoResponseDTO.class);
	}
	
	public List<MovimientoResponseDTO> toResponseDTOList(List<MovimientoInventario> movimientos) {
		return movimientos.stream().map(this::toResponseDTO).toList();
	}
}
