package com.example.micropromocioneskafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private String idProducto;
    private Integer cantidad;
    private Double precio;
} 