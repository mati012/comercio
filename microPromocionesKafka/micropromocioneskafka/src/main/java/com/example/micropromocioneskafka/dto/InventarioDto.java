package com.example.micropromocioneskafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDto {
    private String idProducto;
    private Integer stock;
} 