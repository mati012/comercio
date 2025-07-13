package com.example.microventakafka.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "catalogos")
public class CatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoEntity> productos;

}
