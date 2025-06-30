package com.example.venta.dto;

import com.example.venta.dto.enums.EventoMensajeEnum;
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