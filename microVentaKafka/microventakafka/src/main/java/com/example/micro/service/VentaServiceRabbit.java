package com.example.micro.service;


import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.entities.DetalleVentaEntity;

import java.util.List;

public interface VentaServiceRabbit {
    List<DetalleVentaEntity> getListaDetalleVenta();
    void guardarDetalleVenta(DetalleVentaDto detalleVentaDto);
    void actualizarDetalleVenta(DetalleVentaDto detalleVentaDto);
    void eliminarDetalleVentaById(Long id);
}
