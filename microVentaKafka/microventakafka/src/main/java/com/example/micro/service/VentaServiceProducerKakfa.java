package com.example.micro.service;

import com.example.micro.dto.DetalleVentaDto;

public interface VentaServiceProducerKakfa {

    void enviarMensajeKafkaVenta(DetalleVentaDto detalleVentaDto);

}
