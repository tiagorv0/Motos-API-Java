package com.vazzoller.motosapi.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private static final String MOTORCYCLE_YEAR_2024_CREATED = "motorcycle.year.2024.created";

    // Configuração do MessageConverter para JSON
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    // Exchange padrão (Topic Exchange)
    @Bean
    public TopicExchange defaultExchange() {
        return new TopicExchange("motosapi.exchange", true, false);
    }

    @Bean
    public Queue motorcycleYear2024Queue() {
        return QueueBuilder.durable(MOTORCYCLE_YEAR_2024_CREATED).build();
    }

    @Bean
    public Binding motorcycleYear2024Binding(Queue motorcycleYear2024Queue, TopicExchange defaultExchange) {
        return BindingBuilder
                .bind(motorcycleYear2024Queue)
                .to(defaultExchange)
                .with(MOTORCYCLE_YEAR_2024_CREATED);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
