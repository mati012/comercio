package com.example.venta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class DetalleVentaDto {

    private Long idCarrito;
    private double totalVenta;
}
