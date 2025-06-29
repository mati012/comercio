package com.example.venta.service;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.model.DetalleVentaEntity;

import java.util.List;

public interface VentaService {
    List<DetalleVentaEntity> getListaDetalleVenta();
    void guardarDetalleVenta(DetalleVentaDto detalleVentaDto);
    void actualizarDetalleVenta(DetalleVentaDto detalleVentaDto);
    void eliminarDetalleVentaById(Long id);
}
