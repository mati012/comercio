package com.example.microventakafka.controller;

import com.example.microventakafka.dto.DetalleVentaDto;
import com.example.microventakafka.entities.DetalleVentaEntity;
import com.example.microventakafka.service.VentaServiceConsumer;
import com.example.microventakafka.service.VentaServiceProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class KafkaControllerProducer {

    private final VentaServiceProducer ventaServiceProducer;
    private final VentaServiceConsumer ventaServiceConsumer;

    public KafkaControllerProducer(VentaServiceProducer ventaServiceProducer, VentaServiceConsumer ventaServiceConsumer) {
        this.ventaServiceProducer = ventaServiceProducer;
        this.ventaServiceConsumer = ventaServiceConsumer;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaEntity>> obtenerDetalleVentas(){
        List<DetalleVentaEntity> detalleVentaEntities = ventaServiceConsumer.obtenerDetalleVentas();
        return ResponseEntity.ok(detalleVentaEntities);
    }

    // Productor ventas
    @PostMapping("/kafka/detalleVentas")
    public ResponseEntity<String> registrarDetalleVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaServiceProducer.enviarMensajeKafkaVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente y se guarda en cola");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalleVentaById(@PathVariable("id") Long id){
        ventaServiceConsumer.eliminarDetalleVenta(id);
        return ResponseEntity.ok("Se ha eliminado correctamente el detalle de venta de la base de datos con id: " + id);
    }


}
