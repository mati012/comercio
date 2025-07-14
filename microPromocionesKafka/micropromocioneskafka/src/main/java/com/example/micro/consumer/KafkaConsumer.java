package com.example.micro.consumer;

import com.example.micro.model.EventoActualizacionInventario;
import com.example.micro.model.EventoVenta;
import com.example.micro.service.PromocionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    
    private final PromocionService promocionService;
    
    /**
     * Consume mensajes del tópico 'stock' para actualizaciones de inventario
     */
    @KafkaListener(topics = "stock", groupId = "promociones-group", containerFactory = "kafkaListenerContainerFactoryStock")
    public void consumirActualizacionStock(EventoActualizacionInventario inventario) {
        log.info("📦 Recibida actualización de stock: {}", inventario);
        
        try {
            // Procesar la actualización de stock
            promocionService.procesarActualizacionStock(inventario);
            log.info("✅ Actualización de stock procesada correctamente");
        } catch (Exception e) {
            log.error("❌ Error al procesar actualización de stock: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Consume mensajes del tópico 'ventas' para notificaciones de ventas
     */
    @KafkaListener(topics = "ventas", groupId = "promociones-group", containerFactory = "kafkaListenerContainerFactoryVentas")
    public void consumirNotificacionVenta(EventoVenta venta) {
        log.info("💰 Recibida notificación de venta: {}", venta);
        
        try {
            // Procesar la notificación de venta
            promocionService.procesarNotificacionVenta(venta);
            log.info("✅ Notificación de venta procesada correctamente");
        } catch (Exception e) {
            log.error("❌ Error al procesar notificación de venta: {}", e.getMessage(), e);
        }
    }
} 