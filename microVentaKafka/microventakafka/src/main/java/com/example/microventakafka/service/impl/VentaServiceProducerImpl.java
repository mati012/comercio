package com.example.microventakafka.service.impl;

import com.example.microventakafka.dto.DetalleVentaDto;
import com.example.microventakafka.service.VentaServiceProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VentaServiceProducerImpl implements VentaServiceProducer {

    private final KafkaTemplate<String, DetalleVentaDto> kafkaTemplate;

    public VentaServiceProducerImpl(KafkaTemplate<String, DetalleVentaDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarMensajeKafkaVenta(DetalleVentaDto detalleVentaDto) {
        log.info("Se envia detalle de venta a la cola");
        kafkaTemplate.send("detalleVentas", detalleVentaDto);
    }

}
