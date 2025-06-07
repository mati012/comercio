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
    private CarritoService service;

    @GetMapping
    public List<CarritoEntity> listar() {
        return service.listarCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarCarritoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarritoEntity crear(@RequestBody CarritoEntity c) {
        return service.crearCarrito(c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/productos")
    public CarritoProductoEntity agregarProducto(@PathVariable Long id, @RequestParam Long productoId, @RequestParam int cantidad) {
        return service.agregarProductoACarrito(id, productoId, cantidad);
    }

    @PutMapping("/producto/{carritoProductoId}")
    public CarritoProductoEntity editarCantidad(@PathVariable Long carritoProductoId, @RequestParam int cantidad) {
        return service.editarCantidad(carritoProductoId, cantidad);
    }

    @DeleteMapping("/producto/{carritoProductoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long carritoProductoId) {
        service.eliminarProductoDeCarrito(carritoProductoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/productos")
    public List<CarritoProductoEntity> listarProductos(@PathVariable Long id) {
        return service.listarProductosDeCarrito(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> crearVentaDesdeCarrito(@PathVariable Long id) {
//        service.crearVentaDesdeCarrito(id);
        return ResponseEntity.ok("Se crea venta");
    }


}