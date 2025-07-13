package com.example.micropromocioneskafka.config;

import com.example.micropromocioneskafka.dto.InventarioDto;
import com.example.micropromocioneskafka.dto.VentaDto;
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
    public ConsumerFactory<String, InventarioDto> consumerFactoryStock() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "promociones-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<InventarioDto> deserializer = new JsonDeserializer<>(InventarioDto.class);
        deserializer.addTrustedPackages("com.example.micropromocioneskafka.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, InventarioDto> kafkaListenerContainerFactoryStock() {
        ConcurrentKafkaListenerContainerFactory<String, InventarioDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryStock());
        return factory;
    }

    // ——— CONSUMIDOR PARA VentaDto (tópico "ventas") ———
    @Bean
    public ConsumerFactory<String, VentaDto> consumerFactoryVentas() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "promociones-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<VentaDto> deserializer = new JsonDeserializer<>(VentaDto.class);
        deserializer.addTrustedPackages("com.example.micropromocioneskafka.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, VentaDto> kafkaListenerContainerFactoryVentas() {
        ConcurrentKafkaListenerContainerFactory<String, VentaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryVentas());
        return factory;
    }
} 