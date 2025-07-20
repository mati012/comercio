package com.example.micro.service.impl;

import com.example.micro.dto.DetalleVentaDto;
import com.example.micro.dto.MensajeVenta;
import com.example.micro.dto.enums.EventoMensajeEnum;
import com.example.micro.entities.DetalleVentaEntity;
import com.example.micro.mapper.DetalleVentaMapper;
import com.example.micro.repository.DetalleVentaRepository;
import com.example.micro.service.VentaServiceRabbit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VentaServiceRabbitImpl implements VentaServiceRabbit {

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
    public void actualizarDetalleVenta(DetalleVentaDto detalleVentaDto) {
        try {
            MensajeVenta mensajeVenta = MensajeVenta.builder()
                    .eventoMensajeEnum(EventoMensajeEnum.ACTUALIZAR)
                    .detalleVentaDto(detalleVentaDto)
                    .build();

            String mensajeJson = objectMapper.writeValueAsString(mensajeVenta);
            rabbitTemplate.convertAndSend("myQueue", mensajeJson);
            log.info("Enviado mensaje de actualización a RabbitMQ: " + mensajeJson);

        } catch (Exception e) {
            log.error("Error al enviar mensaje de actualización", e);
        }

    }

    @Override
    public void eliminarDetalleVentaById(Long id) {
        try {
            MensajeVenta mensajeVentaAEliminar = MensajeVenta.builder()
                    .eventoMensajeEnum(EventoMensajeEnum.ELIMINAR)
                    .detalleVentaDto(DetalleVentaDto.builder()
                            .id(id)
                            .build())
                    .build();

            String mensajeJson = objectMapper.writeValueAsString(mensajeVentaAEliminar);
            rabbitTemplate.convertAndSend("myQueue", mensajeJson);
            log.info("Enviado mensaje a eliminar a RabbitMQ: " + mensajeJson);

        } catch (Exception e) {
            log.error("Error eliminando mensaje: ", e);
        }
    }

}
