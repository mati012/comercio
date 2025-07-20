package com.example.micro.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
