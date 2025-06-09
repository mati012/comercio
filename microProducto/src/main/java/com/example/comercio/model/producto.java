package com.example.comercio.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Lob
@Column(name = "imagen_url")
private String imagenUrl;

    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;

    @OneToMany(mappedBy = "producto")
    private List<carritoProducto> carritos;
    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private catalogo catalogo;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<carritoProducto> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<carritoProducto> carritos) {
        this.carritos = carritos;
    }

    public catalogo getCatalogo() {
        return catalogo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setCatalogo(catalogo catalogo) {
        this.catalogo = catalogo;
    }
}
