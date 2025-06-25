package com.example.consumidorpromociones.listener;

import com.example.consumidorpromociones.dto.PromocionDto;
import com.example.consumidorpromociones.util.JsonWriter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PromocionListener {

    @Value("${directorio.salida}")
    private String directorioSalida;

    @RabbitListener(queues = "${cola.promociones.nombre}")
    public void recibirPromocion(PromocionDto promocion) {
        System.out.println("📥 Promoción recibida: " + promocion.getDescripcion());
        JsonWriter.escribirJson(promocion, directorioSalida);
    }
}
