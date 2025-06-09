package com.example.comercio.service;

import com.example.comercio.model.producto;
import com.example.comercio.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productoServiceImpl implements productoService {

    @Autowired
    private productoRepository productoRepo;

    @Override
    public List<producto> listarProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Optional<producto> buscarPorId(Long id) {
        return productoRepo.findById(id);
    }

    @Override
    public producto crear(producto p) {
        return productoRepo.save(p);
    }

    @Override
    public producto editar(Long id, producto actualizado) {
        producto p = productoRepo.findById(id).orElseThrow();
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