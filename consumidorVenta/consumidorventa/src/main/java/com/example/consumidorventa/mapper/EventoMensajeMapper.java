package com.example.consumidorventa.mapper;

import com.example.consumidorventa.dto.DetalleVentaDto;
import com.example.consumidorventa.dto.EventoMensajeDto;
import com.example.consumidorventa.model.DetalleVentaEntity;
import org.springframework.stereotype.Component;

@Component
public class EventoMensajeMapper {

    public DetalleVentaEntity eventoMensajeDtoToEntity(DetalleVentaDto detalleVentaDto){
        return DetalleVentaEntity.builder()
                .id(detalleVentaDto.getId())
                .idCarrito(detalleVentaDto.getIdCarrito())
                .totalVenta(detalleVentaDto.getTotalVenta())
                .build();
    }
}
