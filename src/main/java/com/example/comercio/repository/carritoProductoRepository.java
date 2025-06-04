package com.example.comercio.repository;

import com.example.comercio.model.carritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface carritoProductoRepository extends JpaRepository<carritoProducto, Long> {
}