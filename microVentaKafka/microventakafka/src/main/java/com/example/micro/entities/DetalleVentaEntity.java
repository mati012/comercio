package com.example.micro.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DETALLE_VENTAS")
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_CARRITO")
    private Long idCarrito;

    @Column(name = "TOTAL_VENTA")
    private double totalVenta;

}
