// PromocionDto.java
package com.example.consumidorpromociones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDto implements Serializable {
    private Long id;
    private String descripcion;
    private double descuento;
}
