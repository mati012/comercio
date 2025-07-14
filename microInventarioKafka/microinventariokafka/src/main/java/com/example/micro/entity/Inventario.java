package com.example.micro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Inventario {

    @Id
    private String idProducto;
    private int stock;

    // Getters y setters
    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
