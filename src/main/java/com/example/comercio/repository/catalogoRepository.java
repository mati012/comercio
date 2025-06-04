package com.example.comercio.repository;

import com.example.comercio.model.catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catalogoRepository extends JpaRepository<catalogo, Long> {
}
// 