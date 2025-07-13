package com.example.microinventariokafka.service;

import com.example.microinventariokafka.entity.Inventario;
import com.example.microinventariokafka.model.EventoVenta;
import com.example.microinventariokafka.model.EventoActualizacionInventario;
import com.example.microinventariokafka.repository.InventarioRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
/**
 * Servicio que consume ventas, actualiza inventario y publica el nuevo stock
 */
@Service
@Slf4j
public class InventarioService {

    private final InventarioRepository repository;
    private final KafkaTemplate<String, EventoActualizacionInventario> kafkaTemplate;

    public InventarioService(InventarioRepository repository,
                             KafkaTemplate<String, EventoActualizacionInventario> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Transactional
    @KafkaListener(topics = "ventas", containerFactory = "kafkaListenerContainerFactory")
    public void procesarVenta(EventoVenta venta) {
          log.debug("🎧 [Listener] llegó venta: {}", venta);
        
        Inventario inv = repository.findById(venta.getIdProducto())
            .orElseGet(() -> {
                Inventario nuevo = new Inventario();
                nuevo.setIdProducto(venta.getIdProducto());
                nuevo.setStock(0);
                return nuevo;
            });
        log.debug("🔄 stock antes: {}, después: {}", inv.getStock() + venta.getCantidadVendida(), inv.getStock());
        int stockActualizado = inv.getStock() - venta.getCantidadVendida();
        inv.setStock(stockActualizado);
        repository.save(inv);

        EventoActualizacionInventario evento = new EventoActualizacionInventario();
        log.debug("📤 Publicando evento stock: {}", evento);
        evento.setIdProducto(inv.getIdProducto());
        evento.setNuevoNivelStock(stockActualizado);
        kafkaTemplate
        .send("stock", evento)
        .thenAccept(result -> log.info("✅ Enviado a stock, offset={} ", result.getRecordMetadata().offset()))
        .exceptionally(ex -> {
            log.error("❌ Error al enviar a stock", ex);
            return null;
        });
    }
}
