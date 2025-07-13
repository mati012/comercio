package com.example.microventakafka.dto;

import com.example.microventakafka.entities.CarritoProductoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CarritoDto {

    private Long id;
    private List<CarritoProductoEntity> productos;

}
