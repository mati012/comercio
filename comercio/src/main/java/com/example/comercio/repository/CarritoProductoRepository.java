package com.example.comercio.repository;

import com.example.comercio.model.CarritoProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProductoEntity, Long> {
}