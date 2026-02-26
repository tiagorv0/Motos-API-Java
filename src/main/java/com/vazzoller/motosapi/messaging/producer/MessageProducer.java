package com.vazzoller.motosapi.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Classe genérica para envio de mensagens RabbitMQ.
 * Use esta classe para publicar mensagens em qualquer fila.
 */
@Lazy
@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String DEFAULT_EXCHANGE = "motosapi.exchange";

    /**
     * Envia uma mensagem para uma fila específica usando o exchange padrão.
     *
     * @param routingKey A chave de roteamento (ex: "rental.created")
     * @param message    O objeto a ser enviado (será convertido para JSON automaticamente)
     */
    public void send(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE, routingKey, message);
    }

    /**
     * Envia uma mensagem para um exchange e routing key específicos.
     *
     * @param exchange   Nome do exchange
     * @param routingKey A chave de roteamento
     * @param message    O objeto a ser enviado
     */
    public void send(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
