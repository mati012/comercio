package com.example.micro.service.impl;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.service.VentaServiceProducerKakfa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VentaServiceProducerKafkaImpl implements VentaServiceProducerKakfa {

    private final KafkaTemplate<String, DetalleVentaDto> kafkaTemplate;

    public VentaServiceProducerKafkaImpl(KafkaTemplate<String, DetalleVentaDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarMensajeKafkaVenta(DetalleVentaDto detalleVentaDto) {
        log.info("Se envia detalle de venta a la cola");
        kafkaTemplate.send("detalleVentas", detalleVentaDto);
    }

}
