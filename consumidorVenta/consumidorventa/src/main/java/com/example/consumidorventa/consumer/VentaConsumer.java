package com.example.consumidorventa.consumer;

import com.example.consumidorventa.config.RabbitMQConfig;
import com.example.consumidorventa.model.DetalleVentaEntity;
import com.example.consumidorventa.repository.DetalleVentaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VentaConsumer {

    private final DetalleVentaRepository repository;
    private final ObjectMapper objectMapper;

    public VentaConsumer(DetalleVentaRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void recibirMensaje(String mensajeJson) {
        try {
            DetalleVentaEntity venta = objectMapper.readValue(mensajeJson, DetalleVentaEntity.class);
            repository.save(venta);
            System.out.println("✅ Venta guardada en Oracle: " + venta);
        } catch (Exception e) {
            System.err.println("❌ Error al procesar mensaje: " + mensajeJson);
            e.printStackTrace();
        }
    }
}
