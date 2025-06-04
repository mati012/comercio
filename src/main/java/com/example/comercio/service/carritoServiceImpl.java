
package com.example.comercio.service;

import com.example.comercio.model.carrito;
import com.example.comercio.model.carritoProducto;
import com.example.comercio.model.producto;
import com.example.comercio.repository.carritoProductoRepository;
import com.example.comercio.repository.carritoRepository;
import com.example.comercio.repository.productoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class carritoServiceImpl implements carritoService {

    @Autowired
    private carritoRepository carritoRepo;

    @Autowired
    private productoRepository productoRepo;

    @Autowired
    private carritoProductoRepository cpRepo;

    @Override
    public List<carrito> listarCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    public Optional<carrito> buscarCarritoPorId(Long id) {
        return carritoRepo.findById(id);
    }

    @Override
    public carrito crearCarrito(carrito nuevo) {
        return carritoRepo.save(nuevo);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepo.deleteById(id);
    }

    @Override
    public carritoProducto agregarProductoACarrito(Long carritoId, Long productoId, int cantidad) {
        carrito carrito = carritoRepo.findById(carritoId).orElseThrow();
        producto producto = productoRepo.findById(productoId).orElseThrow();

        carritoProducto cp = new carritoProducto();
        cp.setCarrito(carrito);
        cp.setProducto(producto);
        cp.setCantidad(cantidad);

        return cpRepo.save(cp);
    }

    @Override
    public void eliminarProductoDeCarrito(Long carritoProductoId) {
        cpRepo.deleteById(carritoProductoId);
    }

    @Override
    public carritoProducto editarCantidad(Long carritoProductoId, int nuevaCantidad) {
        carritoProducto cp = cpRepo.findById(carritoProductoId).orElseThrow();
        cp.setCantidad(nuevaCantidad);
        return cpRepo.save(cp);
    }

    @Override
    public List<carritoProducto> listarProductosDeCarrito(Long carritoId) {
        carrito carrito = carritoRepo.findById(carritoId).orElseThrow();
        return carrito.getProductos();
    }
}
