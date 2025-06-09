package com.example.venta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
