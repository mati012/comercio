package com.example.micro.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDto {

    private Long id;
    private Long idCarrito;
    private double totalVenta;
}
