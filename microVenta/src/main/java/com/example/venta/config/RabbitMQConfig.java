package com.example.venta.config;

import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String MAIN_QUEUE = "myQueue";
	public static final String DLX_EXCHANGE = "dlx-exchange";
	public static final String DLX_QUEUE = "dlx-queue";
	public static final String DLX_ROUTING_KEY = "dlx-routing-key";

	@Bean
	Queue myQueue() {
		return new Queue(MAIN_QUEUE, true, false, false,
				Map.of("x-dead-letter-exchange", DLX_EXCHANGE, "x-dead-letter-routing-key", DLX_ROUTING_KEY));
	}

	@Bean
	DirectExchange dlxExchange() {
		return new DirectExchange(DLX_EXCHANGE);
	}

	@Bean
	Queue dlxQueue() {
		return new Queue(DLX_QUEUE);
	}

	@Bean
	Binding dlxBinding() {
		return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(DLX_ROUTING_KEY);
	}
}
