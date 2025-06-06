package com.example.comercio.feign;

import com.example.comercio.dto.DetalleVentaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-ventas", url = "${ms.ventas.url}")
public interface VentaFeignClient {

    @PostMapping("/api/ventas/registrar")
    void registrarVenta(@RequestBody DetalleVentaDto detalle);
}