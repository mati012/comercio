package com.example.comercio.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
public class DetalleVentaDto {


    @NotNull
    private Long idCarrito;
    @NotNull
    private double totalVenta;
}
