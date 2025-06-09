package com.example.venta.service;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.model.DetalleVentaEntity;
import com.example.venta.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VentaServiceImpl implements VentaService{


    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) {
        detalleVentaRepository.save(DetalleVentaEntity.builder()
                        .idCarrito(detalleVentaDto.getIdCarrito())
                        .totalVenta(detalleVentaDto.getTotalVenta())
                .build());
    }
}
