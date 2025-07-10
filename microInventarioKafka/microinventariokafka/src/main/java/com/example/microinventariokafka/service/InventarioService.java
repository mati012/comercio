package com.example.microinventariokafka.service;

import com.example.microinventariokafka.entity.Inventario;
import com.example.microinventariokafka.model.EventoVenta;
import com.example.microinventariokafka.model.EventoActualizacionInventario;
import com.example.microinventariokafka.repository.InventarioRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que consume ventas, actualiza inventario y publica el nuevo stock
 */
@Service

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
        Inventario inv = repository.findById(venta.getIdProducto())
            .orElseGet(() -> {
                Inventario nuevo = new Inventario();
                nuevo.setIdProducto(venta.getIdProducto());
                nuevo.setStock(0);
                return nuevo;
            });

        int stockActualizado = inv.getStock() - venta.getCantidadVendida();
        inv.setStock(stockActualizado);
        repository.save(inv);

        EventoActualizacionInventario evento = new EventoActualizacionInventario();
        evento.setIdProducto(inv.getIdProducto());
        evento.setNuevoNivelStock(stockActualizado);
        kafkaTemplate.send("stock", evento);
    }
}
