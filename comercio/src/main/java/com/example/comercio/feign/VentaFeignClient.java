package com.example.comercio.feign;

import com.example.comercio.dto.DetalleVentaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "ms-ventas", url = "http://localhost:8081")
public interface VentaFeignClient {

    @PostMapping(value = "/api/ventas/registrar")
    void registrarVenta(@RequestBody DetalleVentaDto detalle);
}