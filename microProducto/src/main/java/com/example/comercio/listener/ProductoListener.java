package com.example.comercio.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductoListener {

    @RabbitListener(queues = "${cola.mensajes.nombre}")
    public void recibirMensaje(String mensaje) {
        System.out.println("📥 [RabbitMQ] Mensaje recibido: " + mensaje);
    }
}
