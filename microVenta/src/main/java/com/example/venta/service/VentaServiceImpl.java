package com.example.venta.service;

import com.example.venta.dto.DetalleVentaDto;
import com.example.venta.mapper.DetalleVentaMapper;
import com.example.venta.model.DetalleVentaEntity;
import com.example.venta.repository.DetalleVentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<DetalleVentaEntity> getListaDetalleVenta() {

        List<DetalleVentaEntity> listaDetalleVentaEntities = detalleVentaRepository.findAll();
        return listaDetalleVentaEntities;
    }

    @Override
    public void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) {
        try {
            String detalleVentaDtoToString = objectMapper.writeValueAsString(detalleVentaDto);
            rabbitTemplate.convertAndSend("myQueue", detalleVentaDtoToString);
            log.info("🥳Enviado a RabbitMQ: " + detalleVentaDtoToString);
        } catch (Exception e) {
            log.error("😭Error al convertir a JSON o enviar a RabbitMQ");
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarDetalleVenta(DetalleVentaDto detalleVentaDto) {
        try {
            Optional<DetalleVentaEntity> detalleVentaEntity = detalleVentaRepository.findById(detalleVentaDto.getIdCarrito());
            if (detalleVentaEntity.isPresent()) {
                DetalleVentaDto nuevoDetalleVentaDto = DetalleVentaDto.builder()
                        .totalVenta(detalleVentaEntity.get().getTotalVenta())
                        .build();
                DetalleVentaEntity nuevoDetalleVentaEntity = detalleVentaMapper.detalleVentaDtoToEntity(nuevoDetalleVentaDto);
                detalleVentaRepository.save(nuevoDetalleVentaEntity);
            }

        } catch (Exception e){
            log.error("Error al actualizar detalle de la venta");
        }

    }

    @Override
    public void eliminarDetalleVentaById(Long id) {
        detalleVentaRepository.deleteById(id);
    }

}
