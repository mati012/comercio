package com.example.micro.model;


public class EventoVenta {
    private String idProducto;
    private int cantidadVendida;

    // Getters y setters
    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }

    public int getCantidadVendida() { return cantidadVendida; }
    public void setCantidadVendida(int cantidadVendida) { this.cantidadVendida = cantidadVendida; }
}
