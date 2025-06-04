package com.example.comercio.model;

import jakarta.persistence.*;

@Entity
public class carritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private carrito carrito;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private producto producto;

    private int cantidad;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public carrito getCarrito() { return carrito; }
    public void setCarrito(carrito carrito) { this.carrito = carrito; }
    public producto getProducto() { return producto; }
    public void setProducto(producto producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
