package com.vazzoller.motosapi.domain.exception;

// 409 - Conflito (ex: email já existe)
public class ConflictException extends BusinessException {
    public ConflictException(String message) {
        super(message);
    }
}
