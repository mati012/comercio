package com.example.microventakafka.repository;

import com.example.microventakafka.entities.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {
}
