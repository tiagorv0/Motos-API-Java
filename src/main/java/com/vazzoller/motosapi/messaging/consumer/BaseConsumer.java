package com.vazzoller.motosapi.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe base abstrata para consumers RabbitMQ.
 * Estenda esta classe para criar consumers específicos.
 * 
 * IMPORTANTE: Cada classe filha deve adicionar @RabbitListener(queues = "nome.da.fila")
 * no método receiveMessage() ou criar seu próprio método anotado.
 *
 * @param <T> Tipo da mensagem que será consumida
 */
public abstract class BaseConsumer<T> implements MessageConsumer<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Método que será chamado pelo RabbitMQ quando uma mensagem chegar.
     * Este método deve ser sobrescrito nas classes filhas com @RabbitListener.
     */
    public void receiveMessage(T message) {
        try {
            logger.info("Mensagem recebida na fila {}: {}", getQueueName(), message);
            consume(message);
            logger.info("Mensagem processada com sucesso na fila {}", getQueueName());
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem na fila {}: {}", getQueueName(), e.getMessage(), e);
            handleError(message, e);
        }
    }

    /**
     * Método abstrato que deve ser implementado pelas classes filhas
     * para processar a mensagem específica.
     */
    @Override
    public abstract void consume(T message);

    /**
     * Método para tratamento de erros. Pode ser sobrescrito para comportamento customizado.
     *
     * @param message Mensagem que causou o erro
     * @param error   Exceção ocorrida
     */
    protected void handleError(T message, Exception error) {
        // Implementação padrão: apenas loga o erro
        // Você pode sobrescrever para enviar para DLQ, retry, etc.
        logger.error("Erro não tratado na fila {}: {}", getQueueName(), error.getMessage());
    }
}
