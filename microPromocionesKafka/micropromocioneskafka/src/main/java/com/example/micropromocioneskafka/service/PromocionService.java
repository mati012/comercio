package com.example.micropromocioneskafka.service;

import com.example.micropromocioneskafka.dto.InventarioDto;
import com.example.micropromocioneskafka.dto.VentaDto;
import com.example.micropromocioneskafka.entity.Promocion;
import com.example.micropromocioneskafka.repository.PromocionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromocionService {
    
    private final PromocionRepository promocionRepository;
    private final RestTemplate restTemplate;
    
    /**
     * Procesa actualizaciones de stock desde Kafka
     */
    @Transactional
    public void procesarActualizacionStock(InventarioDto inventario) {
        log.info("🔄 Procesando actualización de stock para producto: {}", inventario.getIdProducto());
        
        // Si el stock es alto, considerar crear una promoción
        if (inventario.getStock() > 10) { // Umbral configurable
            crearPromocionPorStock(inventario);
        }
    }
    
    /**
     * Procesa notificaciones de ventas desde Kafka
     */
    @Transactional
    public void procesarNotificacionVenta(VentaDto venta) {
        log.info("🔄 Procesando notificación de venta para producto: {}", venta.getIdProducto());
        
        // Aquí podrías implementar lógica adicional basada en ventas
        // Por ejemplo, desactivar promociones si el stock baja mucho
    }
    
    /**
     * Genera promociones basadas en productos con más stock (On Demand)
     */
    @Transactional
    public Promocion generarPromocionOnDemand() {
        log.info("🎯 Generando promoción On Demand...");
        
        try {
            // Obtener inventario completo del microservicio de inventario
            InventarioDto[] inventarios = restTemplate.getForObject(
                "http://localhost:8082/api/inventario", 
                InventarioDto[].class
            );
            
            if (inventarios == null || inventarios.length == 0) {
                log.warn("⚠️ No se encontraron productos en el inventario");
                return null;
            }
            
            // Encontrar el producto con más stock
            InventarioDto productoConMasStock = inventarios[0];
            for (InventarioDto inv : inventarios) {
                if (inv.getStock() > productoConMasStock.getStock()) {
                    productoConMasStock = inv;
                }
            }
            
            log.info("📈 Producto con más stock: {} (Stock: {})", 
                    productoConMasStock.getIdProducto(), productoConMasStock.getStock());
            
            // Crear promoción para el producto con más stock
            return crearPromocionPorStock(productoConMasStock);
            
        } catch (Exception e) {
            log.error("❌ Error al generar promoción On Demand: {}", e.getMessage(), e);
            throw new RuntimeException("Error al generar promoción", e);
        }
    }
    
    /**
     * Crea una promoción para un producto con alto stock
     */
    private Promocion crearPromocionPorStock(InventarioDto inventario) {
        // Verificar si ya existe una promoción activa para este producto
        Optional<Promocion> promocionExistente = promocionRepository
            .findByIdProductoAndActivaTrue(inventario.getIdProducto());
        
        if (promocionExistente.isPresent()) {
            log.info("ℹ️ Ya existe una promoción activa para el producto: {}", inventario.getIdProducto());
            return promocionExistente.get();
        }
        
        // Crear nueva promoción
        Promocion nuevaPromocion = new Promocion();
        nuevaPromocion.setIdProducto(inventario.getIdProducto());
        nuevaPromocion.setDescripcion(String.format("¡Oferta especial! Producto %s con %d unidades disponibles", 
                inventario.getIdProducto(), inventario.getStock()));
        nuevaPromocion.setDescuento(25.0); // 25% de descuento
        nuevaPromocion.setStockDisponible(inventario.getStock());
        nuevaPromocion.setActiva(true);
        
        Promocion promocionGuardada = promocionRepository.save(nuevaPromocion);
        log.info("✅ Promoción creada: {}", promocionGuardada);
        
        return promocionGuardada;
    }
    
    /**
     * Obtiene todas las promociones activas
     */
    public List<Promocion> obtenerPromocionesActivas() {
        return promocionRepository.findByActivaTrue();
    }
    
    /**
     * Desactiva una promoción
     */
    @Transactional
    public void desactivarPromocion(Long id) {
        Optional<Promocion> promocion = promocionRepository.findById(id);
        if (promocion.isPresent()) {
            promocion.get().setActiva(false);
            promocionRepository.save(promocion.get());
            log.info("🔴 Promoción desactivada: {}", id);
        }
    }
} 