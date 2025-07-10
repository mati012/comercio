package com.example.microinventariokafka.repository;

// Update the import below to match the actual package of Inventario
import com.example.microinventariokafka.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Operaciones CRUD sobre Inventario
 */
public interface InventarioRepository extends JpaRepository<Inventario, String> { }
