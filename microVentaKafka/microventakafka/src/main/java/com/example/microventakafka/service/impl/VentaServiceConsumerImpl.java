package com.example.microventakafka.service.impl;

import com.example.microventakafka.dto.DetalleVentaDto;
import com.example.microventakafka.entities.DetalleVentaEntity;
import com.example.microventakafka.repository.DetalleVentaRepository;
import com.example.microventakafka.service.VentaServiceConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class VentaServiceConsumerImpl implements VentaServiceConsumer {

    private final DetalleVentaRepository detalleVentaRepository;

    public VentaServiceConsumerImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Transactional
    @KafkaListener(topics = "detalleVentas", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) throws Exception {
        DetalleVentaEntity entity = DetalleVentaEntity.builder()
                .idCarrito(detalleVentaDto.getIdCarrito())
                .totalVenta(detalleVentaDto.getTotalVenta())
                .build();

        if (detalleVentaDto.getId() != null) {
            // Valida si es que existe el id y hace la actualizacion
            detalleVentaRepository.findById(detalleVentaDto.getId()).orElseThrow( () -> new Exception("No existe id para actualizar"));
            entity.setId(detalleVentaDto.getId());
        }
        DetalleVentaEntity entityCreada = detalleVentaRepository.save(entity);
        log.info("Se consume mensaje de detalle de venta desde kafka: [{}]", entityCreada);
    }
}
