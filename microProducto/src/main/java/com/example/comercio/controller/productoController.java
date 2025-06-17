package com.example.comercio.controller;

import com.example.comercio.model.producto;
import com.example.comercio.service.productoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class productoController {
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @Value("${cola.mensajes.nombre}")
    private String queue;

    @Autowired
    private productoService service;

    @GetMapping
    public List<producto> listar() {
        return service.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<String> enviarMensaje(@RequestBody producto p) {
        System.out.println("🔥 Llamado a /productos/enviar");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String mensaje = mapper.writeValueAsString(p);
            amqpTemplate.convertAndSend(queue, mensaje);
            return ResponseEntity.ok("📤 Mensaje enviado a RabbitMQ");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error al enviar mensaje");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody producto p) {
        return ResponseEntity.ok(service.editar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }


} 