package com.example.microventakafka.service;

import com.example.microventakafka.dto.DetalleVentaDto;
import com.example.microventakafka.entities.DetalleVentaEntity;

import java.util.List;

public interface VentaServiceConsumer {

    List<DetalleVentaEntity> obtenerDetalleVentas();
    void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) throws Exception;
    void eliminarDetalleVenta(Long id);
}
