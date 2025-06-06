package com.example.comercio.service;

import com.example.comercio.model.ProductoEntity;
import com.example.comercio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public List<ProductoEntity> listarProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Optional<ProductoEntity> buscarPorId(Long id) {
        return productoRepo.findById(id);
    }

    @Override
    public ProductoEntity crear(ProductoEntity p) {
        return productoRepo.save(p);
    }

    @Override
    public ProductoEntity editar(Long id, ProductoEntity actualizado) {
        ProductoEntity p = productoRepo.findById(id).orElseThrow();
        p.setNombre(actualizado.getNombre());
        p.setDescripcion(actualizado.getDescripcion());
        p.setPrecio(actualizado.getPrecio());
        p.setCategoria(actualizado.getCategoria());
        return productoRepo.save(p);
    }

    @Override
    public void eliminar(Long id) {
        productoRepo.deleteById(id);
    }
}