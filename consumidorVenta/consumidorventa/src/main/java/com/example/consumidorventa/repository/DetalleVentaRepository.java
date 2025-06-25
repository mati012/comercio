package com.example.consumidorventa.repository;

import com.example.consumidorventa.model.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {
}
