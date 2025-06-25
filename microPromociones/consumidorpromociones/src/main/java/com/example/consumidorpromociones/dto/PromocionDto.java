package com.example.consumidorpromociones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocionDto {
    private Long id;
    private String descripcion;
    private double descuento;
}
