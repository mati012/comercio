package com.example.micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoVenta {
    private String idProducto;
    private int cantidadVendida;
}