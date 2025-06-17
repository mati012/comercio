package com.example.comercio.model;

import com.example.comercio.model.producto;
import com.example.comercio.service.productoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MensajeListener {

    @Autowired
    private productoService service;

    @RabbitListener(queues = "${cola.mensajes.nombre}")
    public void recibirMensaje(String mensaje) {
        try {
            System.out.println("📥 Recibido desde RabbitMQ: " + mensaje);
            ObjectMapper mapper = new ObjectMapper();
            producto p = mapper.readValue(mensaje, producto.class);
            producto guardado = service.crear(p);
            System.out.println("✅ Guardado en Oracle: " + guardado.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
