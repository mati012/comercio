package com.example.venta.service;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.model.DetalleVentaEntity;
import com.example.venta.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;  // para convertir a JSON

   @Override
public void guardarDetalleVenta(DetalleVentaDto dto) {
    try {
        String json = objectMapper.writeValueAsString(dto);
        rabbitTemplate.convertAndSend("myQueue", json);
        System.out.println("🟢 Enviado a RabbitMQ: " + json);
    } catch (Exception e) {
        System.err.println("❌ Error al convertir a JSON o enviar a RabbitMQ");
        e.printStackTrace();
    }
}

}
