package com.example.comercio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<carritoProducto> productos;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long idCarrito) { this.id = idCarrito; }
    public List<carritoProducto> getProductos() { return productos; }
    public void setProductos(List<carritoProducto> productos) { this.productos = productos; }
}
