package com.example.microventakafka.config;

import com.example.microventakafka.dto.DetalleVentaDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public NewTopic ventasTopic() {
        return TopicBuilder.name("detalleVentas")
                .partitions(3)
                .replicas(1)
                .build();
    }

    // ——— PRODUCTOR PARA DetalleVenta ———
    @Bean
    public ProducerFactory<String, DetalleVentaDto> producerFactoryVentas() {
        Map<String, Object> props = kafkaProperties.buildProducerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, DetalleVentaDto> kafkaTemplateVentas() {
        return new KafkaTemplate<>(producerFactoryVentas());
    }


    // ——— Consumidor para DetalleVenta ———
    @Bean
    public ConsumerFactory<String, DetalleVentaDto> consumerFactoryVentas() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-micro-venta");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<DetalleVentaDto> deserializer = new JsonDeserializer<>(DetalleVentaDto.class);
        deserializer.addTrustedPackages("com.example.microventakafka.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DetalleVentaDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DetalleVentaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryVentas());
        return factory;
    }
}