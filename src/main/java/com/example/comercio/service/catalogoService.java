// === Interfaz catalogoService ===
package com.example.comercio.service;

import com.example.comercio.model.catalogo;
import java.util.List;
import java.util.Optional;

public interface catalogoService {
    List<catalogo> listarCatalogos();
    Optional<catalogo> buscarPorId(Long id);
    catalogo crear(catalogo c);
    catalogo editar(Long id, catalogo actualizado);
    void eliminar(Long id);
}
