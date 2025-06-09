package com.example.comercio.controller;

import com.example.comercio.model.CarritoEntity;
import com.example.comercio.model.CarritoProductoEntity;
import com.example.comercio.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<CarritoEntity> listarCarrito() {
        return carritoService.listarCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCarritoPorId(@PathVariable Long id) {
        return carritoService.buscarCarritoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarritoEntity crearCarrito(@RequestBody CarritoEntity c) {
        return carritoService.crearCarrito(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/productos")
    public CarritoProductoEntity agregarProducto(@PathVariable Long id, @RequestParam Long productoId, @RequestParam int cantidad) {
        return carritoService.agregarProductoACarrito(id, productoId, cantidad);
    }

    @PutMapping("/producto/{carritoProductoId}")
    public CarritoProductoEntity editarCantidad(@PathVariable Long carritoProductoId, @RequestParam int cantidad) {
        return carritoService.editarCantidad(carritoProductoId, cantidad);
    }

    @DeleteMapping("/producto/{carritoProductoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long carritoProductoId) {
        carritoService.eliminarProductoDeCarrito(carritoProductoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/productos")
    public List<CarritoProductoEntity> listarProductos(@PathVariable Long id) {
        return carritoService.listarProductosDeCarrito(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> crearVentaDesdeCarrito(@PathVariable Long id) {
        carritoService.crearVentaDesdeCarrito(id);
        return ResponseEntity.ok("Se crea venta");
    }


}