package com.example.comercio.service;

import com.example.comercio.model.CatalogoEntity;
import com.example.comercio.repository.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private CatalogoRepository catalogoRepo;

    @Override
    public List<CatalogoEntity> listarCatalogos() {
        return catalogoRepo.findAll();
    }

    @Override
    public Optional<CatalogoEntity> buscarPorId(Long id) {
        return catalogoRepo.findById(id);
    }

    @Override
    public CatalogoEntity crear(CatalogoEntity c) {
        return catalogoRepo.save(c);
    }

    @Override
    public CatalogoEntity editar(Long id, CatalogoEntity actualizado) {
        CatalogoEntity c = catalogoRepo.findById(id).orElseThrow();
        c.setNombre(actualizado.getNombre());
        c.setDescripcion(actualizado.getDescripcion());
        return catalogoRepo.save(c);
    }

    @Override
    public void eliminar(Long id) {
        catalogoRepo.deleteById(id);
    }
}