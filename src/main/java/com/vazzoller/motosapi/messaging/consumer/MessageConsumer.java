package com.vazzoller.motosapi.messaging.consumer;

/**
 * Interface base para consumers de mensagens RabbitMQ.
 * Implemente esta interface para criar consumers específicos.
 *
 * @param <T> Tipo da mensagem que será consumida
 */
public interface MessageConsumer<T> {
    
    /**
     * Processa uma mensagem recebida da fila.
     *
     * @param message A mensagem a ser processada
     */
    void consume(T message);
    
    /**
     * Retorna o nome da fila que este consumer escuta.
     *
     * @return Nome da fila
     */
    String getQueueName();
}
