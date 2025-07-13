package com.example.microventakafka.controller;

import com.example.microventakafka.dto.DetalleVentaDto;
import com.example.microventakafka.service.VentaServiceProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KafkaControllerProducer {

    private final VentaServiceProducer ventaServiceProducer;

    public KafkaControllerProducer(VentaServiceProducer ventaServiceProducer) {
        this.ventaServiceProducer = ventaServiceProducer;
    }

    // Productor ventas
    @PostMapping("/kafka/ventas")
    public ResponseEntity<String> registrarDetalleVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaServiceProducer.enviarMensajeKafkaVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente y se guarda en cola");
    }

}
