package com.example.micro.config;

import com.example.micro.model.EventoActualizacionInventario;
import com.example.micro.model.EventoVenta;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    // ——— CONSUMIDOR PARA InventarioDto (tópico "stock") ———
    @Bean
    public ConsumerFactory<String, EventoActualizacionInventario> consumerFactoryStock() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "promociones-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        JsonDeserializer<EventoActualizacionInventario> deserializer = new JsonDeserializer<>(EventoActualizacionInventario.class);
        deserializer.addTrustedPackages("com.example.micro.model");
        deserializer.setUseTypeMapperForKey(false);
        deserializer.setRemoveTypeHeaders(false);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventoActualizacionInventario> kafkaListenerContainerFactoryStock() {
        ConcurrentKafkaListenerContainerFactory<String, EventoActualizacionInventario> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryStock());
        return factory;
    }

    // ——— CONSUMIDOR PARA VentaDto (tópico "ventas") ———
    @Bean
    public ConsumerFactory<String, EventoVenta> consumerFactoryVentas() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "promociones-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<EventoVenta> deserializer = new JsonDeserializer<>(EventoVenta.class);
        deserializer.addTrustedPackages("com.example.micro.model");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventoVenta> kafkaListenerContainerFactoryVentas() {
        ConcurrentKafkaListenerContainerFactory<String, EventoVenta> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryVentas());
        return factory;
    }
} 