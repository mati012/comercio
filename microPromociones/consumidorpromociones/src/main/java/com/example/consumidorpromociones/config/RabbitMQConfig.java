package com.example.consumidorpromociones.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${cola.promociones.nombre}")
    private String nombreCola;

    @Bean
    public Queue promocionQueue() {
        return new Queue(nombreCola, true);
    }
}
