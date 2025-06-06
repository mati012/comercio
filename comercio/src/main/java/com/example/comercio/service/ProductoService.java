package com.example.comercio.service;

import com.example.comercio.model.ProductoEntity;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<ProductoEntity> listarProductos();
    Optional<ProductoEntity> buscarPorId(Long id);
    ProductoEntity crear(ProductoEntity p);
    ProductoEntity editar(Long id, ProductoEntity actualizado);
    void eliminar(Long id);
}
