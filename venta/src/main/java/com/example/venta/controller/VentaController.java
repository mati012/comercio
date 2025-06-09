package com.example.venta.controller;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaService.guardarDetalleVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente");
    }

}
