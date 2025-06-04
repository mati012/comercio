
// === Interfaz carritoService ===

package com.example.comercio.service;

import com.example.comercio.model.carrito;
import com.example.comercio.model.carritoProducto;

import java.util.List;
import java.util.Optional;

public interface carritoService {

    List<carrito> listarCarritos();

    Optional<carrito> buscarCarritoPorId(Long id);

    carrito crearCarrito(carrito nuevo);

    void eliminarCarrito(Long id);

    carritoProducto agregarProductoACarrito(Long carritoId, Long productoId, int cantidad);

    void eliminarProductoDeCarrito(Long carritoProductoId);

    carritoProducto editarCantidad(Long carritoProductoId, int nuevaCantidad);

    List<carritoProducto> listarProductosDeCarrito(Long carritoId);
}
