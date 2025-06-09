// === Servicio para catalogo ===
package com.example.comercio.service;

import com.example.comercio.model.catalogo;
import com.example.comercio.repository.catalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class catalogoServiceImpl implements catalogoService {

    @Autowired
    private catalogoRepository catalogoRepo;

    @Override
    public List<catalogo> listarCatalogos() {
        return catalogoRepo.findAll();
    }

    @Override
    public Optional<catalogo> buscarPorId(Long id) {
        return catalogoRepo.findById(id);
    }

    @Override
    public catalogo crear(catalogo c) {
        return catalogoRepo.save(c);
    }

    @Override
    public catalogo editar(Long id, catalogo actualizado) {
        catalogo c = catalogoRepo.findById(id).orElseThrow();
        c.setNombre(actualizado.getNombre());
        c.setDescripcion(actualizado.getDescripcion());
        return catalogoRepo.save(c);
    }

    @Override
    public void eliminar(Long id) {
        catalogoRepo.deleteById(id);
    }
}