package com.example.comercio.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carrito_productos")
public class CarritoProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private CarritoEntity carrito;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private ProductoEntity producto;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

}
