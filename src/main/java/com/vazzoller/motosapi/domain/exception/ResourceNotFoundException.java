package com.vazzoller.motosapi.domain.exception;

// 404 - Recurso não encontrado
public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s com ID %d não encontrado", resource, id));
    }
}
