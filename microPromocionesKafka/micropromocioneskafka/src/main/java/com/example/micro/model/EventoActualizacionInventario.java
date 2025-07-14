package com.example.micro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoActualizacionInventario {
    private String idProducto;
    private int nuevoNivelStock;
} 