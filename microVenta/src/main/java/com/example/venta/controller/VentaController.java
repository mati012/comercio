package com.example.venta.controller;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.mapper.DetalleVentaMapper;
import com.example.venta.model.DetalleVentaEntity;
import com.example.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<DetalleVentaDto>> obtenerListaDetalleVentas(){
        List<DetalleVentaEntity> listaDetalleVentaEntity = ventaService.getListaDetalleVenta();

        List<DetalleVentaDto> listaDetalleVentaDto = listaDetalleVentaEntity.stream()
                .map(detalleVentaMapper::detalleVentaEntityToDto)
                .toList();

        return new ResponseEntity<>(listaDetalleVentaDto, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaService.guardarDetalleVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente");
    }

    @PutMapping
    public ResponseEntity<String> actualizarVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaService.actualizarDetalleVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha actualizado la venta exitosamente");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarVenta(Long id) {
        ventaService.eliminarDetalleVentaById(id);
        return ResponseEntity.ok("Se elimina la venta correctamente");
    }

}
