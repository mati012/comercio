package com.example.comercio.service;

import com.example.comercio.dto.DetalleVentaDto;
import com.example.comercio.dto.ProductoDto;
import com.example.comercio.feign.VentaFeignClient;
import com.example.comercio.model.CarritoEntity;
import com.example.comercio.model.CarritoProductoEntity;
import com.example.comercio.model.ProductoEntity;
import com.example.comercio.repository.CarritoProductoRepository;
import com.example.comercio.repository.CarritoRepository;
import com.example.comercio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private VentaFeignClient ventaFeignClient;


    @Autowired
    private CarritoRepository carritoRepo;

    @Override
    public List<CarritoEntity> listarCarritos() {
        return carritoRepository.findAll();
    }

    @Override
    public Optional<CarritoEntity> buscarCarritoPorId(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public CarritoEntity crearCarrito(CarritoEntity nuevo) {
        return carritoRepository.save(nuevo);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public CarritoProductoEntity agregarProductoACarrito(Long carritoId, Long productoId, int cantidad) {
        CarritoEntity carritoEntity = carritoRepository.findById(carritoId).orElseThrow();
        ProductoEntity productoEntity = productoRepository.findById(productoId).orElseThrow();

        CarritoProductoEntity carritoProductoEntity = CarritoProductoEntity.builder()
                .carrito(carritoEntity)
                .producto(productoEntity)
                .cantidad(cantidad)
                .build();

        return carritoProductoRepository.save(carritoProductoEntity);
    }

    @Override
    public void eliminarProductoDeCarrito(Long carritoProductoId) {
        carritoProductoRepository.deleteById(carritoProductoId);
    }

    @Override
    public CarritoProductoEntity editarCantidad(Long carritoProductoId, int nuevaCantidad) {
        CarritoProductoEntity carritoProductoEntity = carritoProductoRepository.findById(carritoProductoId).orElseThrow();
        carritoProductoEntity.setCantidad(nuevaCantidad);
        return carritoProductoRepository.save(carritoProductoEntity);
    }

    @Override
    public List<CarritoProductoEntity> listarProductosDeCarrito(Long carritoId) {
        CarritoEntity carritoEntity = carritoRepository.findById(carritoId).orElseThrow();
        return carritoEntity.getProductos();
    }

    @Override
    public void crearVentaDesdeCarrito(Long carritoId) {
        CarritoEntity carrito = carritoRepo.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

        List<ProductoEntity> productos = carrito.getProductos().stream()
                .map(CarritoProductoEntity::getProducto).toList();
        List<ProductoDto> listaProductos = productos.stream().map(this::productoEntityaDto).toList();

        ventaFeignClient.registrarVenta(DetalleVentaDto.builder()
                .idCarrito(carrito.getId())
                .totalVenta(getTotal(listaProductos))
                .build());
    }

    private ProductoDto productoEntityaDto(ProductoEntity productoEntity) {
        return ProductoDto.builder()
                .nombre(productoEntity.getNombre())
                .descripcion(productoEntity.getDescripcion())
                .precio(productoEntity.getPrecio())
                .categoria(productoEntity.getCategoria())
                .build();

    }

    private static double getTotal(List<ProductoDto> listaProductos) {
        return listaProductos.stream().mapToDouble(ProductoDto::getPrecio).sum();
    }


}
