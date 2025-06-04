package com.example.comercio.controller;

import com.example.comercio.model.catalogo;
import com.example.comercio.service.catalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@CrossOrigin(origins = "*")
public class catalogoController {

    @Autowired
    private catalogoService service;

    @GetMapping
    public List<catalogo> listar() {
        return service.listarCatalogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public catalogo crear(@RequestBody catalogo c) {
        return service.crear(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody catalogo c) {
        return ResponseEntity.ok(service.editar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

