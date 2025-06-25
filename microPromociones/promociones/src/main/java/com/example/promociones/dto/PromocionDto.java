package com.example.promociones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocionDto implements Serializable {
    private Long id;
    private String descripcion;
    private double descuento;
}
