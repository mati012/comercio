package com.example.microventakafka.service;

import com.example.microventakafka.dto.DetalleVentaDto;

public interface VentaServiceConsumer {

    void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) throws Exception;
}
