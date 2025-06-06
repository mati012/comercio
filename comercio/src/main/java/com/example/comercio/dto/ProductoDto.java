package com.example.comercio.dto;

import com.example.comercio.model.CarritoProductoEntity;
import com.example.comercio.model.CatalogoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String categoria;

}
