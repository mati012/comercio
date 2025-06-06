package com.example.comercio.service;

import com.example.comercio.model.CarritoEntity;
import com.example.comercio.model.CarritoProductoEntity;

import java.util.List;
import java.util.Optional;

public interface CarritoService {

    List<CarritoEntity> listarCarritos();

    Optional<CarritoEntity> buscarCarritoPorId(Long id);

    CarritoEntity crearCarrito(CarritoEntity nuevo);

    void eliminarCarrito(Long id);

    CarritoProductoEntity agregarProductoACarrito(Long carritoId, Long productoId, int cantidad);

    void eliminarProductoDeCarrito(Long carritoProductoId);

    CarritoProductoEntity editarCantidad(Long carritoProductoId, int nuevaCantidad);

    List<CarritoProductoEntity> listarProductosDeCarrito(Long carritoId);
}
