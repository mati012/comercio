package com.example.venta.mapper;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.model.DetalleVentaEntity;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {

    public DetalleVentaDto detalleVentaEntityToDto(DetalleVentaEntity detalleVentaEntity) {
        return DetalleVentaDto.builder()
                .id(detalleVentaEntity.getId())
                .idCarrito(detalleVentaEntity.getIdCarrito())
                .totalVenta(detalleVentaEntity.getTotalVenta())
                .build();
    }

    public DetalleVentaEntity detalleVentaDtoToEntity(DetalleVentaDto detalleVentaDto) {
        return DetalleVentaEntity.builder()
                .idCarrito(detalleVentaDto.getIdCarrito())
                .totalVenta(detalleVentaDto.getTotalVenta())
                .build();
    }

}
