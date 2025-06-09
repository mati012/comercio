package com.example.comercio.controller;

import com.example.comercio.model.CatalogoEntity;
import com.example.comercio.service.CatalogoService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@CrossOrigin(origins = "*")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public List<CatalogoEntity> listarCatalogo() {
        return catalogoService.listarCatalogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCatalogoPorId(@PathVariable Long id) {
        return catalogoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CatalogoEntity crearCatalogo(@RequestBody CatalogoEntity c) {
        return catalogoService.crear(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCatalogo(@PathVariable Long id, @RequestBody CatalogoEntity c) {
        return ResponseEntity.ok(catalogoService.editar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCatalogo(@PathVariable Long id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

