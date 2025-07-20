package com.example.micro.controller;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.entities.DetalleVentaEntity;
import com.example.micro.service.VentaServiceConsumerKafka;
import com.example.micro.service.VentaServiceProducerKakfa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class KafkaControllerProducer {

    private final VentaServiceProducerKakfa ventaServiceProducerKakfa;
    private final VentaServiceConsumerKafka ventaServiceConsumerKafka;

    public KafkaControllerProducer(VentaServiceProducerKakfa ventaServiceProducerKakfa, VentaServiceConsumerKafka ventaServiceConsumerKafka) {
        this.ventaServiceProducerKakfa = ventaServiceProducerKakfa;
        this.ventaServiceConsumerKafka = ventaServiceConsumerKafka;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaEntity>> obtenerDetalleVentas(){
        List<DetalleVentaEntity> detalleVentaEntities = ventaServiceConsumerKafka.obtenerDetalleVentas();
        return ResponseEntity.ok(detalleVentaEntities);
    }

    // Productor ventas
    @PostMapping("/kafka/detalleVentas")
    public ResponseEntity<String> registrarDetalleVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaServiceProducerKakfa.enviarMensajeKafkaVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente y se guarda en cola");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDetalleVentaById(@PathVariable("id") Long id){
        ventaServiceConsumerKafka.eliminarDetalleVenta(id);
        return ResponseEntity.ok("Se ha eliminado correctamente el detalle de venta de la base de datos con id: " + id);
    }


}
