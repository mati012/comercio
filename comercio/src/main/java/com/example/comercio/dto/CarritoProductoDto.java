package com.example.comercio.dto;

import com.example.comercio.model.CarritoEntity;
import com.example.comercio.model.ProductoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarritoProductoDto {

    private Long id;
    private CarritoEntity carritoEntity;
    private ProductoEntity productoEntity;
    private int cantidad;

}
