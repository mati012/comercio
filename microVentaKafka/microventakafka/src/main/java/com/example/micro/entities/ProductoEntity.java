package com.example.micro.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio")
    private double precio;
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProductoEntity> carrito;
    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private CatalogoEntity catalogo;

}
