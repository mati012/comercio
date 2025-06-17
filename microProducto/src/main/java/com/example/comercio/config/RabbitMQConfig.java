package com.example.comercio.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${cola.mensajes.nombre}")
    private String queueName;

    @Bean
    public Queue colaMensajes() {
        return new Queue(queueName, false);
    }
}
