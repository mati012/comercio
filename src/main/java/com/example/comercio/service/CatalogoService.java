package com.example.comercio.service;

import com.example.comercio.model.CatalogoEntity;
import java.util.List;
import java.util.Optional;

public interface CatalogoService {
    List<CatalogoEntity> listarCatalogos();
    Optional<CatalogoEntity> buscarPorId(Long id);
    CatalogoEntity crear(CatalogoEntity c);
    CatalogoEntity editar(Long id, CatalogoEntity actualizado);
    void eliminar(Long id);
}
