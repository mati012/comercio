package com.example.comercio.repository;

import com.example.comercio.model.carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface carritoRepository extends JpaRepository<carrito, Long> {
}
