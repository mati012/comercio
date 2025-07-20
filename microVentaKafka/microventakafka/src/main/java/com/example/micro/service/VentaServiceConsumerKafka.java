package com.example.micro.service;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.entities.DetalleVentaEntity;

import java.util.List;

public interface VentaServiceConsumerKafka {

    List<DetalleVentaEntity> obtenerDetalleVentas();
    void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) throws Exception;
    void eliminarDetalleVenta(Long id);
}
