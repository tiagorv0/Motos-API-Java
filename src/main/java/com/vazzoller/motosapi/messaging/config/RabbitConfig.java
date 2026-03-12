package com.vazzoller.motosapi.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.StatelessRetryOperationsInterceptor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
public class RabbitConfig {
    private static final String MOTORCYCLE_YEAR_2024_CREATED = "motorcycle.year.2024.created";
    private static final String MOTORCYCLE_YEAR_2024_CREATED_DLQ = "motorcycle.year.2024.created.dlq";
    private static final String DEFAULT_EXCHANGE = "motosapi.exchange";
    private static final String DEAD_LETTER_EXCHANGE = "motosapi.dlx";

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public TopicExchange defaultExchange() {
        return new TopicExchange(DEFAULT_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    @Bean
    public Queue motorcycleYear2024Queue() {
        return QueueBuilder.durable(MOTORCYCLE_YEAR_2024_CREATED)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", MOTORCYCLE_YEAR_2024_CREATED_DLQ)
                .build();
    }

    @Bean
    public Queue motorcycleYear2024DeadLetterQueue() {
        return QueueBuilder.durable(MOTORCYCLE_YEAR_2024_CREATED_DLQ).build();
    }

    @Bean
    public Binding motorcycleYear2024Binding(Queue motorcycleYear2024Queue, TopicExchange defaultExchange) {
        return BindingBuilder
                .bind(motorcycleYear2024Queue)
                .to(defaultExchange)
                .with(MOTORCYCLE_YEAR_2024_CREATED);
    }

    @Bean
    public Binding motorcycleYear2024DeadLetterBinding(Queue motorcycleYear2024DeadLetterQueue,
                                                       DirectExchange deadLetterExchange) {
        return BindingBuilder
                .bind(motorcycleYear2024DeadLetterQueue)
                .to(deadLetterExchange)
                .with(MOTORCYCLE_YEAR_2024_CREATED_DLQ);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public StatelessRetryOperationsInterceptor retryOperationsInterceptor(RabbitTemplate rabbitTemplate) {
        RepublishMessageRecoverer recoverer = new RepublishMessageRecoverer(
                rabbitTemplate,
                DEAD_LETTER_EXCHANGE,
                MOTORCYCLE_YEAR_2024_CREATED_DLQ
        );

        return RetryInterceptorBuilder.StatelessRetryInterceptorBuilder.stateless()
                .maxRetries(3)
                .backOffOptions(1000, 2.0, 10000)
                .recoverer(recoverer)
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            StatelessRetryOperationsInterceptor retryOperationsInterceptor) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        factory.setAdviceChain(retryOperationsInterceptor);
        factory.setDefaultRequeueRejected(false);
        return factory;
    }
}
