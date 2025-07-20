package com.example.micro.dto;

import com.example.micro.dto.enums.EventoMensajeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MensajeVenta {
    private EventoMensajeEnum eventoMensajeEnum;
    private DetalleVentaDto detalleVentaDto;

}