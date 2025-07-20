package com.example.micro.controller;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.entities.DetalleVentaEntity;
import com.example.micro.mapper.DetalleVentaMapper;
import com.example.micro.service.VentaServiceRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin
public class RabbitControllerProducer {

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    @Autowired
    private VentaServiceRabbit ventaServiceRabbit;

    @GetMapping
    public ResponseEntity<List<DetalleVentaDto>> obtenerListaDetalleVentas(){
        List<DetalleVentaEntity> listaDetalleVentaEntity = ventaServiceRabbit.getListaDetalleVenta();

        List<DetalleVentaDto> listaDetalleVentaDto = listaDetalleVentaEntity.stream()
                .map(detalleVentaMapper::detalleVentaEntityToDto)
                .toList();

        return new ResponseEntity<>(listaDetalleVentaDto, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaServiceRabbit.guardarDetalleVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha registra la venta correctamente");
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarVenta(@RequestBody DetalleVentaDto detalleVentaDto) {
        ventaServiceRabbit.actualizarDetalleVenta(detalleVentaDto);
        return ResponseEntity.ok("Se ha actualizado la venta exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {
        ventaServiceRabbit.eliminarDetalleVentaById(id);
        return ResponseEntity.ok("Se elimina la venta correctamente");
    }

}
