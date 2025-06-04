package com.example.comercio.controller;

import com.example.comercio.model.carrito;
import com.example.comercio.model.carritoProducto;
import com.example.comercio.service.carritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class carritoController {

    @Autowired
    private carritoService service;

    @GetMapping
    public List<carrito> listar() {
        return service.listarCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarCarritoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public carrito crear(@RequestBody carrito c) {
        return service.crearCarrito(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/productos")
    public carritoProducto agregarProducto(@PathVariable Long id, @RequestParam Long productoId, @RequestParam int cantidad) {
        return service.agregarProductoACarrito(id, productoId, cantidad);
    }

    @PutMapping("/producto/{carritoProductoId}")
    public carritoProducto editarCantidad(@PathVariable Long carritoProductoId, @RequestParam int cantidad) {
        return service.editarCantidad(carritoProductoId, cantidad);
    }

    @DeleteMapping("/producto/{carritoProductoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long carritoProductoId) {
        service.eliminarProductoDeCarrito(carritoProductoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/productos")
    public List<carritoProducto> listarProductos(@PathVariable Long id) {
        return service.listarProductosDeCarrito(id);
    }
}