package com.example.micro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "promociones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_producto", nullable = false)
    private String idProducto;
    
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @Column(name = "descuento", nullable = false)
    private Double descuento;
    
    @Column(name = "stock_disponible")
    private Integer stockDisponible;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "activa", nullable = false)
    private Boolean activa;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (activa == null) {
            activa = true;
        }
    }
} 