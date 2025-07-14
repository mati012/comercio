package com.example.micro.model;


public class EventoActualizacionInventario {
    private String idProducto;
    private int nuevoNivelStock;

    // Getters y setters
    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }

    public int getNuevoNivelStock() { return nuevoNivelStock; }
    public void setNuevoNivelStock(int nuevoNivelStock) { this.nuevoNivelStock = nuevoNivelStock; }
}
