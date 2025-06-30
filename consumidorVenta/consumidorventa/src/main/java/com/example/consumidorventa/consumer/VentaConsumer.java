package com.example.consumidorventa.consumer;

import com.example.consumidorventa.config.RabbitMQConfig;
import com.example.consumidorventa.dto.DetalleVentaDto;
import com.example.consumidorventa.dto.EventoMensajeDto;
import com.example.consumidorventa.dto.enums.EventoMensajeEnum;
import com.example.consumidorventa.mapper.EventoMensajeMapper;
import com.example.consumidorventa.model.DetalleVentaEntity;
import com.example.consumidorventa.repository.DetalleVentaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class VentaConsumer {

    private final DetalleVentaRepository repository;
    private final ObjectMapper objectMapper;
    private final EventoMensajeMapper eventoMensajeMapper;

    public VentaConsumer(DetalleVentaRepository repository, ObjectMapper objectMapper, EventoMensajeMapper eventoMensajeMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        this.eventoMensajeMapper = eventoMensajeMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void recibirMensaje(String mensajeJson) {
        try {
            EventoMensajeDto venta = objectMapper.readValue(mensajeJson, EventoMensajeDto.class);
            evaluarEventoMensaje(venta);
            System.out.println("Venta guardada en Oracle: " + venta);
        } catch (Exception e) {
            System.err.println("Error al procesar mensaje: " + mensajeJson);
            e.printStackTrace();
        }
    }

    private void evaluarEventoMensaje(EventoMensajeDto eventoMensajeDto) {

        if (eventoMensajeDto.getEventoMensajeEnum() == null) {
            log.warn("Evento recibido con tipo de evento nulo");
            return;
        }
        DetalleVentaDto detalleVentaDto = eventoMensajeDto.getDetalleVentaDto();
        EventoMensajeEnum evento = eventoMensajeDto.getEventoMensajeEnum();

        switch (evento) {
            case CREAR:
                repository.save(eventoMensajeMapper.eventoMensajeDtoToEntity(detalleVentaDto));
                log.info("Detalle de venta creado: ID {}", detalleVentaDto.getId());
                break;
            case ACTUALIZAR:
                Optional<DetalleVentaEntity> detalleOptional = repository.findById(detalleVentaDto.getId());
                if (detalleOptional.isPresent()){
                    DetalleVentaEntity detalleVentaEntity = eventoMensajeMapper.eventoMensajeDtoToEntity(eventoMensajeDto.getDetalleVentaDto());
                    repository.save(detalleVentaEntity);
                } else {
                    log.error("No existe id carrito: ", detalleVentaDto.getId());
                }
                break;
            case ELIMINAR:
                Optional<DetalleVentaEntity> eliminarOpt = repository.findById(detalleVentaDto.getId());
                if (eliminarOpt.isPresent()){
                    repository.deleteById(detalleVentaDto.getId());
                } else {
                    log.error("Error al eliminar: ", detalleVentaDto.getId());
                }
                break;

            default:
                log.warn("Error tipo evento no reconocido");
        }
    }
}
