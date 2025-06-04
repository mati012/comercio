
// === Interfaz productoService ===
package com.example.comercio.service;

import com.example.comercio.model.producto;
import java.util.List;
import java.util.Optional;

public interface productoService {
    List<producto> listarProductos();
    Optional<producto> buscarPorId(Long id);
    producto crear(producto p);
    producto editar(Long id, producto actualizado);
    void eliminar(Long id);
}
