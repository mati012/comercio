package com.example.promociones.service;

import com.example.promociones.config.RabbitMQConfig;
import com.example.promociones.dto.PromocionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PromocionService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final AtomicLong idGenerator = new AtomicLong(1);

    public PromocionService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

   @Scheduled(fixedRate = 30000)
        public void enviarPromocion() {
            PromocionDto promocion = new PromocionDto(
                    idGenerator.getAndIncrement(),
                    "Descuento del " + (int) (10 + Math.random() * 20) + "%",
                    Math.round((10 + Math.random() * 20) * 100.0) / 100.0
            );

            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.promocion", promocion);
            System.out.println("📢 Promoción enviada: " + promocion);
        }

}
