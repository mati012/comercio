package com.example.micro.controller;

import com.example.micro.entity.Promocion;
import com.example.micro.service.PromocionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promociones")
@RequiredArgsConstructor
@Slf4j
public class PromocionController {
    
    private final PromocionService promocionService;
    
    /**
     * Genera una promoción On Demand basada en el producto con más stock
     */
    @PostMapping("/generar")
    public ResponseEntity<Promocion> generarPromocionOnDemand() {
        log.info("🎯 Solicitud para generar promoción On Demand");
        
        try {
            Promocion promocion = promocionService.generarPromocionOnDemand();
            
            if (promocion != null) {
                return ResponseEntity.ok(promocion);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error("❌ Error al generar promoción: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Obtiene todas las promociones activas
     */
    @GetMapping("/activas")
    public ResponseEntity<List<Promocion>> obtenerPromocionesActivas() {
        log.info("📋 Consultando promociones activas");
        
        try {
            List<Promocion> promociones = promocionService.obtenerPromocionesActivas();
            return ResponseEntity.ok(promociones);
        } catch (Exception e) {
            log.error("❌ Error al obtener promociones activas: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Desactiva una promoción específica
     */
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<String> desactivarPromocion(@PathVariable Long id) {
        log.info("🔴 Solicitud para desactivar promoción: {}", id);
        
        try {
            promocionService.desactivarPromocion(id);
            return ResponseEntity.ok("Promoción desactivada correctamente");
        } catch (Exception e) {
            log.error("❌ Error al desactivar promoción: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Endpoint de salud para verificar que el servicio está funcionando
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("🟢 Microservicio de promociones Kafka funcionando correctamente");
    }
} 