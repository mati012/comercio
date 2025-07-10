package com.example.microinventariokafka.controller;

import com.example.microinventariokafka.entity.Inventario;
import com.example.microinventariokafka.model.EventoVenta;
import com.example.microinventariokafka.repository.InventarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KafkaController {

    private final KafkaTemplate<String, EventoVenta> kafkaTemplate;
    private final InventarioRepository repository;

    public KafkaController(KafkaTemplate<String, EventoVenta> kafkaTemplate,
                          InventarioRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    /**
     * 1) Producir evento de venta en Kafka.
     *    Esto dispara luego el @KafkaListener que procesa la venta.
     */
    @PostMapping("/kafka/ventas")
    public ResponseEntity<Void> enviarEventoVenta(@RequestBody EventoVenta venta) {
        kafkaTemplate.send("ventas", venta);
        return ResponseEntity.accepted().build();
    }

    /**
     * 3) Consultar stock actual en Oracle.
     */
    @GetMapping("/inventario/{idProducto}")
    public ResponseEntity<Inventario> obtenerStock(@PathVariable String idProducto) {
        return repository.findById(idProducto)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
}
