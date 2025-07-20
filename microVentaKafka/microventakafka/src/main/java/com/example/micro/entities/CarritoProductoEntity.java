package com.example.micro.entities;

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
    @JoinColumn(name = "carrito_id")
    private CarritoEntity carrito;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoEntity producto;
    @Column(name = "cantidad")
    private int cantidad;

}
