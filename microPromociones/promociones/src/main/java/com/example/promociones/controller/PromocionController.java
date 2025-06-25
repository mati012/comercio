package com.example.promociones.controller;

import com.example.promociones.config.RabbitMQConfig;
import com.example.promociones.dto.PromocionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final RabbitTemplate rabbitTemplate;

    public PromocionController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String enviarPromocionManual(@RequestBody PromocionDto promocion) {
        try {
         rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routing.promocion", promocion);
            return "✅ Promoción enviada manualmente";
        } catch (Exception e) {
            return "❌ Error al enviar promoción: " + e.getMessage();
        }
    }
}
