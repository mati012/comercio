package com.example.consumidorventa.dto;

import com.example.consumidorventa.dto.enums.EventoMensajeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventoMensajeDto {

    private EventoMensajeEnum eventoMensajeEnum;
    private DetalleVentaDto detalleVentaDto;
}
