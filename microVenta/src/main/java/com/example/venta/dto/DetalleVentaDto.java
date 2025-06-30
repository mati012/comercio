package com.example.venta.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import lombok.Data;
import java.io.Serializable;


@Getter
@Setter
@Builder
@Data
public class DetalleVentaDto implements Serializable {

    private Long id;
    private Long idCarrito;
    private double totalVenta;
}
