package com.example.micro.config;

import com.example.micro.model.EventoActualizacionInventario;
import com.example.micro.model.EventoVenta;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    /** Crea el tópico "ventas" si no existe */
    @Bean
    public NewTopic ventasTopic() {
        return TopicBuilder.name("ventas")
                .partitions(3)
                .replicas(1)
                .build();
    }

    /** Crea el tópico "stock" si no existe */
    @Bean
    public NewTopic stockTopic() {
        return TopicBuilder.name("stock")
                .partitions(3)
                .replicas(1)
                .build();
    }

    // ——— PRODUCTOR PARA EventoVenta ———
    @Bean
    public ProducerFactory<String, EventoVenta> producerFactoryVentas() {
        Map<String, Object> props = kafkaProperties.buildProducerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, EventoVenta> kafkaTemplateVentas() {
        return new KafkaTemplate<>(producerFactoryVentas());
    }

    // ——— PRODUCTOR PARA EventoActualizacionInventario ———
    @Bean
    public ProducerFactory<String, EventoActualizacionInventario> producerFactoryStock() {
        Map<String, Object> props = kafkaProperties.buildProducerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, EventoActualizacionInventario> kafkaTemplateStock() {
        return new KafkaTemplate<>(producerFactoryStock());
    }

    // ——— CONSUMIDOR PARA EventoVenta ———
    @Bean
    public ConsumerFactory<String, EventoVenta> consumerFactoryVentas() {
        // 1) arranca con las props que sí tienes (bootstrap.servers...)
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        // 2) añade el group.id aquí (si no lo pones en properties)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-micro-inv");
        // 3) indicas las clases de los deserializers
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 4) creas la instancia indicando la clase objetivo y el paquete de confianza
        JsonDeserializer<EventoVenta> deserializer = new JsonDeserializer<>(EventoVenta.class);
        deserializer.addTrustedPackages("com.example.micro.model");

        // 5) construyes la fábrica pasándole la instancia
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventoVenta> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EventoVenta> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryVentas());
        return factory;
    }
}