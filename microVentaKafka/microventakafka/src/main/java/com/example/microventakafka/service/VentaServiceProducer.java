package com.example.microventakafka.service;

import com.example.microventakafka.dto.DetalleVentaDto;

public interface VentaServiceProducer {

    void enviarMensajeKafkaVenta(DetalleVentaDto detalleVentaDto);

}
