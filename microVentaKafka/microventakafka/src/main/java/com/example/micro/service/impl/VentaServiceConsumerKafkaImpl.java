package com.example.micro.service.impl;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.dto.MensajeVenta;
import com.example.micro.dto.enums.EventoMensajeEnum;
import com.example.micro.entities.DetalleVentaEntity;
import com.example.micro.repository.DetalleVentaRepository;
import com.example.micro.service.VentaServiceConsumerKafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class VentaServiceConsumerKafkaImpl implements VentaServiceConsumerKafka {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final DetalleVentaRepository detalleVentaRepository;

    public VentaServiceConsumerKafkaImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }


    @Override
    public List<DetalleVentaEntity> obtenerDetalleVentas() {
        return detalleVentaRepository.findAll();
    }

    @Transactional
    @KafkaListener(topics = "detalleVenta", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void guardarDetalleVenta(DetalleVentaDto detalleVentaDto) {
        log.info("Se consume desde kafka y se manda a cola rabbit");
        guardarDetalleVentaRabbit(detalleVentaDto);
    }

    private void guardarDetalleVentaRabbit(DetalleVentaDto detalleVentaDto) {
        try {
            MensajeVenta mensajeVenta = MensajeVenta.builder()
                    .eventoMensajeEnum(EventoMensajeEnum.CREAR)
                    .detalleVentaDto(detalleVentaDto)
                    .build();
            String detalleVentaDtoToString = objectMapper.writeValueAsString(mensajeVenta);
            rabbitTemplate.convertAndSend("myQueue", detalleVentaDtoToString);
            log.info("Enviado a RabbitMQ: " + detalleVentaDtoToString);
        } catch (Exception e) {
            log.error("Error al convertir a JSON o enviar a RabbitMQ");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarDetalleVenta(Long id) {
        detalleVentaRepository.deleteById(id);
    }


}
