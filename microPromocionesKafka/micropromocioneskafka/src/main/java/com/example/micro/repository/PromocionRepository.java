package com.example.micro.repository;

import com.example.micro.entity.Promocion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    
    /**
     * Encuentra promociones activas
     */
    List<Promocion> findByActivaTrue();
    
    /**
     * Encuentra promociones por producto
     */
    List<Promocion> findByIdProducto(String idProducto);
    
    /**
     * Encuentra promociones activas por producto
     */
    Optional<Promocion> findByIdProductoAndActivaTrue(String idProducto);
    
    /**
     * Encuentra promociones ordenadas por stock disponible (mayor a menor)
     */
    @Query("SELECT p FROM Promocion p WHERE p.activa = true ORDER BY p.stockDisponible DESC")
    List<Promocion> findPromocionesActivasOrdenPorStock();
    
    /**
     * Desactiva todas las promociones de un producto
     */
    @Query("UPDATE Promocion p SET p.activa = false WHERE p.idProducto = :idProducto")
    void desactivarPromocionesPorProducto(@Param("idProducto") String idProducto);
} 