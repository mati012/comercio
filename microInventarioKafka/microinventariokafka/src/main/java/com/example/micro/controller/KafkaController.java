package com.example.micro.controller;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.entity.Inventario;
import com.example.micro.model.EventoVenta;
import com.example.micro.repository.InventarioRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api")
public class KafkaController {

    private final KafkaTemplate<String, EventoVenta> kafkaTemplate;
    private final KafkaTemplate<String, DetalleVentaDto> kafkaTemplateDetalleVenta;
    private final InventarioRepository repository;

    public KafkaController(KafkaTemplate<String, EventoVenta> kafkaTemplate, KafkaTemplate<String, DetalleVentaDto> kafkaTemplateDetalleVenta,
                           InventarioRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateDetalleVenta = kafkaTemplateDetalleVenta;
        this.repository = repository;
    }


    /**
     * Devuelve todo el inventario.
     */
    @GetMapping("/inventario")
    public ResponseEntity<List<Inventario>> obtenerTodoElInventario() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * 1) Producir evento de venta en Kafka.
     *    Esto dispara luego el @KafkaListener que procesa la venta.
     */
    @PostMapping("/kafka/ventas")
    public ResponseEntity<Void> enviarEventoVenta(@RequestBody EventoVenta venta) {
        log.info("📤 Enviando evento de venta: {}", venta);

        kafkaTemplate.send("ventas", venta)
        .thenAccept(result -> System.out.println("Evento venta enviado con offset: " + result.getRecordMetadata().offset()))
        .exceptionally(ex -> {
            System.err.println("Error al enviar evento venta: " + ex.getMessage());
            return null;
        });
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/inventario/{idProducto}")
    public ResponseEntity<Inventario> obtenerStock(@PathVariable String idProducto) {
        return repository.findById(idProducto)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/kafka/detalleVenta")
    public ResponseEntity<String> enviarEventoDetalleVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        log.info("📤 Enviando evento de detalle de venta: {}", detalleVentaDto);
        kafkaTemplateDetalleVenta.send("detalleVenta", detalleVentaDto)
                .thenAccept(result -> System.out.println("Evento detalle venta enviado con offset: " + result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    System.err.println("Error al enviar evento detalle venta: " + ex.getMessage());
                    return null;
                });
        return ResponseEntity.accepted().build();
    }
}
